package api.steps;

import java.util.ArrayList;
import java.util.List;

import static constants.Constants.APPLICATION_ID_1;
import static constants.Constants.APPLICATION_ID_2;
import static api.steps.PendingApprovalStep.generatePendingApprovalRequest;

public class PublishedStep extends BaseStep {
    public List<String> listOfPublishedAppId = new ArrayList<>();
    public List<String> listOfRemovedFromPublishedAppId = new ArrayList<>();

    public static PublishedStep generatePublishedAppId() {

        PendingApprovalStep pendingApprovalStep = new PendingApprovalStep();
        PublishedStep publishedStep = new PublishedStep();
        generatePendingApprovalRequest(pendingApprovalStep, APPLICATION_ID_1, APPLICATION_ID_2);
        String requestId = pendingApprovalStep.getListOfPendingApprovalRequests().get(0);
        pendingApprovalStep.approveRequest(requestId, publishedStep);

        return publishedStep;
    }
}
