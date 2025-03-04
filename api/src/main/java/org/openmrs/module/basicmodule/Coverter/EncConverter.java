package org.openmrs.module.basicmodule.Coverter;

import com.google.gson.Gson;
import org.openmrs.module.basicmodule.pojo.custom.CommonData;
import org.openmrs.module.basicmodule.pojo.mrsEncounter.MrsEncounterResponse;
import org.openmrs.module.basicmodule.pojo.request.shrEncounter.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class EncConverter {
    private static final Logger LOGGER = LoggerFactory.getLogger(EncConverter.class);

    public static Object parse(CommonData data, String upi) {
        MrsEncounterResponse response = (MrsEncounterResponse) data.getResponse();
        ShrEncounterRequest request = ShrEncounterRequest.builder()
                .id(response.getUuid())
                .resourceType("Encounter")
                .status("finished")
                .period(Period.builder()
                        .start(response.getEncounterDatetime().replace("+0530", "Z"))
                        .build())
                .identifier(new ArrayList<Identifier>() {{
                    add(Identifier.builder()
                            .value(response.getUuid())
                            .build());
                }})
                .type(new ArrayList<Type>() {{
                    add(Type.builder()
                            .coding(new ArrayList<Coding>() {{
                                add(Coding.builder()
                                        .display(response.getForm().getEncounterType().getDisplay())
                                        .build());
                            }})
                            .build());
                    add(Type.builder()
                            .coding(new ArrayList<Coding>() {{
                                add(Coding.builder()
                                        .display(response.getForm().getDisplay())
                                        .build());
                            }})
                            .build());
                }})
                .subject(Subject.builder()
                        .identifier(Identifier.builder()
                                .value(upi)
                                .type(Type.builder()
                                        .coding(new ArrayList<Coding>() {{
                                            add(Coding.builder()
                                                    .display("UPI")
                                                    .code("UPI")
                                                    .build());
                                        }})
                                        .build())
                                .build())
                        .build())
                .location(new ArrayList<Location>() {{
                    add(Location.builder()
                            .location(Location__1.builder()
                                    .identifier(Identifier.builder()
                                            .value(response.getLocation().getDescription())
                                            .build())
                                    .display(response.getLocation().getDisplay())
                                    .build())
                            .build());
                }})
                .build();

        LOGGER.error(">>>>>>>>>> Enc json : \n\n\n\n" + new Gson().toJson(request));
        return request;
    }


}
