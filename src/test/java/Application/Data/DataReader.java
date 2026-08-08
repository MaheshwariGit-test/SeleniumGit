package Application.Data;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.io.FileUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {

	public static List<HashMap<String,String>> readJsonData(String filePath) throws Throwable {
//		FileInputStream fis=new FileInputStream("C:\\Users\\anger\\eclipse-workspace\\FrameWork\\src\\test\\java\\Application\\Data\\PurchaseOrder.json");
		System.out.println("data Reader class");
		//String to HashMap-->jackson-databind
		String jsonContent=FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8);
		
		ObjectMapper mapper=new ObjectMapper();
		List<HashMap<String,String>> data=mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>() {
		});
		return data;
	}
}
