package api.steps;

import java.util.ArrayList;
import java.util.List;

import static constants.Constants.*;
import static api.steps.PendingApprovalStep.generatePendingApprovalRequest;

public class RejectedStep extends BaseStep {
    public List<String> listOfRejectedAppId = new ArrayList<>();
    public List<String> listOfAppIdRemovedFromRejectedList = new ArrayList<>();


    public static RejectedStep generateRejectedAppId() {


        PendingApprovalStep pendingApprovalStep = new PendingApprovalStep();
        PublishedStep publishedStep = new PublishedStep();
        generatePendingApprovalRequest(pendingApprovalStep, APPLICATION_ID_1, APPLICATION_ID_2);
        String requestId = pendingApprovalStep.getListOfPendingApprovalRequests().get(0);
        pendingApprovalStep.approveRequest(requestId, publishedStep);
        publishedStep.deleteAppIdFromList(ENDPOINT_TO_DELETE_APP_ID_FROM_PUBLISHED_LIST
                , publishedStep.listOfRemovedFromPublishedAppId
                , publishedStep.listOfPublishedAppId
                , APPLICATION_ID_1
                , APPLICATION_ID_2);

        RejectedStep rejectedStep = new RejectedStep();
        rejectedStep.listOfRejectedAppId = new ArrayList<>();
        rejectedStep.listOfRejectedAppId = publishedStep.listOfRemovedFromPublishedAppId;

        return rejectedStep;

    }
}
