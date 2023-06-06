package constants;

public class Constants {


    public static final String APPLICATION_ID_1 = "app@1.1";
    public static final String APPLICATION_ID_2 = "app@1.2";
    public static final String APPLICATION_ID_3 = "app@1.3";
    public static final String APPLICATION_ID_4 = "app@1.4";

    public static final String ENDPOINT_TO_LOGIN = "/api/login";
    public static final String ENDPOINT_TO_LOGOUT = "/logout";

    public static final String SUBMIT_APP_ID_END_POINT = "/api/application";

    public static final String ENDPOINT_TO_GET_SUBMITTED_APP_ID = "/api/applications?status=submit";
    public static final String ENDPOINT_TO_GET_PENDING_APPROVAL_APP_ID = "/api/applications?status=pending";
    public static final String ENDPOINT_TO_DE_GET_PUBLISHED_APP_ID = "/api/applications?status=published";
    public static final String ENDPOINT_TO_DE_GET_REJECTED_APP_ID = "/api/applications?status=rejected";
    public static final String ENDPOINT_TO_DE_GET_LIVE_APP_ID = "/api/applications?status=live";


    public static final String ENDPOINT_TO_DELETE_APP_ID_FROM_REQUEST_FOR_APPROVAL = "/api/application_list?list=submit";
    public static final String ENDPOINT_TO_DELETE_APP_ID_FROM_PENDING_APPROVAL_LIST = "/api/application_list?list=pending";
    public static final String ENDPOINT_TO_DELETE_APP_ID_FROM_PUBLISHED_LIST = "/api/application_list?list=published";
    public static final String ENDPOINT_TO_DELETE_APP_ID_FROM_REJECTION_LIST = "/api/application_list?list=rejected";
    public static final String ENDPOINT_TO_GET_PENDING_APPROVAL_REQUESTS = "/api/actions";

    public static final String ENDPOINT_TO_APPROVE = "/api/approval";
    public static final String ENDPOINT_TO_APPROVE_PENDING_APPROVAL = "/api/approve";

    public static final String ENDPOINT_TO_IMPORT_CSV_FILE = "/api/applications_upload";
}
