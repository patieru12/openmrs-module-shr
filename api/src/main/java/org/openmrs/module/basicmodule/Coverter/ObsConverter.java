package org.openmrs.module.basicmodule.Coverter;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.internal.LinkedTreeMap;
import org.openmrs.module.basicmodule.constants.Sync2Constants;
import org.openmrs.module.basicmodule.pojo.custom.CommonData;
import org.openmrs.module.basicmodule.pojo.mrsObs.MrsObsResponse;
import org.openmrs.module.basicmodule.pojo.mrsObs.Value;
import org.openmrs.module.basicmodule.pojo.request.shrEncounter.Coding;
import org.openmrs.module.basicmodule.pojo.request.shrEncounter.Identifier;
import org.openmrs.module.basicmodule.pojo.request.shrEncounter.Subject;
import org.openmrs.module.basicmodule.pojo.request.shrEncounter.Type;
import org.openmrs.module.basicmodule.pojo.request.shrObservation.Category;
import org.openmrs.module.basicmodule.pojo.request.shrObservation.ShrObservationEncounter;
import org.openmrs.module.basicmodule.pojo.request.shrObservation.ShrObservationRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

public class ObsConverter {


    public static Object parse(CommonData data, String upi) {
        MrsObsResponse response = (MrsObsResponse) data.getResponse();
        List<Category> categoryList = new ArrayList<Category>() {{
            add(Category.builder()
                    .coding(new ArrayList<Coding>() {{
                        add(Coding.builder()
                                .display(response.getConcept().getDisplay())
                                .build());
                    }})
                    .build());
        }};
        ShrObservationRequest.ShrObservationRequestBuilder requestBuilder = ShrObservationRequest.builder()
                .id(response.getUuid())
                .resourceType("Observation")
                .identifier(new ArrayList<Identifier>() {{
                    add(Identifier.builder()
                            .value(response.getUuid())
                            .build());
                }})
                .status(response.getStatus().toLowerCase(Locale.ROOT))
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
                .valueString("NA")
                .effectiveDateTime(response.getObsDatetime().replace("+0530", "Z"));

        if (response.getValue() instanceof String || response.getValue() instanceof Integer || response.getValue() instanceof Double) {
            requestBuilder.valueString(response.getValue().toString());
            if(response.getEncounter()!=null) {
                requestBuilder.encounter(ShrObservationEncounter.builder()
                        .identifier(new ArrayList<Identifier>() {{
                            add(Identifier.builder()
                                    .value(response.getEncounter().getUuid())
                                    .build());
                        }})
                        .build());
            }
        } else if (response.getValue()!=null) {
            requestBuilder.encounter(ShrObservationEncounter.builder()
                    .identifier(new ArrayList<Identifier>() {{
                                    add(Identifier.builder()
                                            .value(response.getEncounter().getUuid())
                                            .build());
                                }})
                    .build());
            LinkedTreeMap value = (LinkedTreeMap) response.getValue();
            categoryList.add(Category.builder()
                    .coding(new ArrayList<Coding>() {{
                        add(Coding.builder()
                                .display(value.get("display").toString())
                                .build());
                    }})
                    .build());
            categoryList.add(Category.builder()
                    .coding(new ArrayList<Coding>() {{
                        add(Coding.builder()
                                .display(((LinkedTreeMap)value.get("conceptClass")).get("display").toString())
                                .build());
                    }})
                    .build());
        }
        requestBuilder.category(categoryList);

        ShrObservationRequest request = requestBuilder.build();

        System.out.println("------------------ " + new Gson().toJson(request));


        return request;
    }


}
