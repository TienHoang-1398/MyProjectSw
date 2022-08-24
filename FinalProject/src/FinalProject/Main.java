package FinalProject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.google.gson.Gson;



public class Main {
	public static RootList getListInfo(int pageNo, int numOfRows){
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builer = factory.newDocumentBuilder();
			
			StringBuffer pharm_url = new StringBuffer();
			pharm_url.append("http://apis.data.go.kr/B552584/EvCharger/getChargerInfo");
			pharm_url.append("?serviceKey=R1jof6NgKLyzzQUX349d9TdjNCYOlItxebh4QimXxAtbsQPVbj%2FtUZAyysKajJuX3e4z1tEAEmo3BN2a6s3S0Q%3D%3D"
					+ "&numOfRows=" +numOfRows+"&pageNo=" +pageNo +"&dataType=JSON");
			URL url = new URL(pharm_url.toString());
			
			HttpURLConnection con =(HttpURLConnection) url.openConnection();
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(),
					"UTF-8"));
			StringBuilder sb = new StringBuilder();
			String input = "";
			while ((input = br.readLine()) != null) {
				sb.append(input);
			}
			br.close();
			con.disconnect();
			Gson gson = new Gson();
		    RootList power = gson.fromJson(sb.toString(),
					RootList.class);
		
			return power;
			}catch (Exception e) {
			e.printStackTrace();
			}
			return null;
			
	}
	public static int getTotalCount() {
		return getListInfo(1, 1).getTotalCount();
	}
		
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int totalCount = getTotalCount();
		System.out.println("현제 전국에 전기차 충전소는 " + totalCount + "개 있습니다");
		
		RootList power = getListInfo(1, totalCount);
		
		Scanner s = new Scanner(System.in);
		while (true) {
			System.out.print("\n시명 또는 지역 입력(X 종료): ");
			String str = s.nextLine();
			if (str.equals("X"))
				break;
			
			System.out.println("=========================");
			for(Datum item : power.getData()) {
				if (item.getAddr().contains(str)|| item.getStatNm().contains(str)){
					System.out.println("충전소명: " +item.getStatNm()+"|" +"충전소ID: " +item.getStatId()+"|"+"충전기ID:" +item.getChgerId());
					System.out.println("충전기타입:" +item.getChgerType());
					System.out.println("충전기 주소:" +item.getAddr());
					System.out.println("충전기 상세위지:" +item.getLocation());
					System.out.println("충전기 위도:" +item.getLat()+"|"+"충전기 경도" +item.getLng());
					System.out.println("충전기 이용가능시간:" +item.getUseTime());
					System.out.println("기관 아이디:" +item.getBusiId()+"|"+"기관명:" +item.getBnm()+"|"+"운영기관명:" +item.getBusiNm()+"|"+
					"운영기관 연략처:" +item.getBusiCall());
					System.out.println("충전기 상태 변경:" +item.getStatUpdDt()+"|"+"마지막 충전시작일시:" +item.getLastTsdt()+"|"+"마지막 충전종료일시:" 
					+item.getLastTedt()+"|"+"충전중 시작일시:" +item.getNowTsdt());
					System.out.println("충전용량 kW (3, 7, 50, 100, 200):" +item.getOutput()+"|"+"충전방식 (단독/동시):" +item.getMethod());
					System.out.println("주차료무료:" +item.getParkingFree()+"|"+"충전소 안내:" +item.getNote());
					System.out.println("이용자 제한:" +item.getLimitYn()+"|"+"이용제한 사유:" +item.getLimitDetail());
					System.out.println("삭제 여부:" +item.getDelYn()+"|"+"삭제 사유:" +item.getDelDetail());
					
					
				}
			}
}		
		System.out.println("종료되었습니다!");
		}

	}

