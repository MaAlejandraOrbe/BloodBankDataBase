/*package bloodBankXMLutilis;

import java.sql.Date;
import java.time.Date;
import java.time.format.DateTimeFormatter;
javax.xml.bind.annotation.adapters.XmlAdapter

public class SQLDateAdapter extends XmlAdapter<String,Date> {

private DateTimeFormatter formatter= DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
	@Override
	public String marshal(Date sqlDate)throws Exception{
		return sqlDate.toDate().format(formatter);
	}
	
	@Override
	public Date unmarshal(String string)throws Exception{
		Date Date=Date.parse(string,formatter);
		return Date.valueOf(Date);
	}
}
*/