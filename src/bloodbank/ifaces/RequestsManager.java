package bloodbank.ifaces;
import java.util.List;
import bloodbank.db.pojos.*;

public interface RequestsManager {
	
	public void insertRequestsWorker(RequestsWorker requestsWorker);
	public RequestsWorker getRequestsWorkerByEmail(String email);
	public RequestsWorker getRequestsWorker(int id);
	
	public void fullfillBloodRequest(BloodRequest bloodRequest);
	public void newBloodRequest(BloodRequest bloodRequest);
	public void newRecipient (Recipient recipient);
	public void newHospital(Hospital hospital);
	public void linkRecipientToBloodRequest(int recipientId, int bloodRequestId);
	public void deleteRecipient(int id);
	
	public List<BloodRequest> getAllBloodRequests();
	public Hospital getHospitalById(int id);
	public Recipient getRecipientById(int id);
	public BloodRequest getBloodRequestById(int id);
	public void updateBloodRequest(BloodRequest bloodrequest);

}


