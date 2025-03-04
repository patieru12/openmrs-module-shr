package org.openmrs.module.basicmodule.pojo;

import lombok.Data;


@Data
public class OpenHimConnection {
        private String openhimUrl;
        private String openhimClientId;
        private String openhimPassword;
        private String status;

        private String loginUserPassword;
        private String loginUserName;
}
