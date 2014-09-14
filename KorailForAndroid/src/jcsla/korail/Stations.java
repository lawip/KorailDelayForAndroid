package jcsla.korail;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public interface Stations {
	Map<String, String> number_name_stations = new HashMap<String, String>() {{
		put("0342", "가수원");
		put("0476", "가야");
		put("0150", "가평");
		put("0309", "각계");
		put("0172", "간현");
		put("0481", "갈촌");
		put("0028", "강경");
		put("0115", "강릉");
		put("0151", "강촌");
		put("0482", "개양");
		put("0219", "개태사");
		put("0160", "개포");
		put("0216", "거제");
		put("0433", "거촌");
		put("0184", "건천");
		put("0208", "경강");
		put("0024", "경산");
		put("0021", "경주");
		put("0468", "경화");
		put("0218", "계룡");
		put("0240", "고막원");
		put("0122", "고한");
		put("0049", "곡성");
		put("0259", "공전");
		put("0370", "관촌");
		put("0491", "광곡");
		put("0501", "광명");
		put("0068", "광양");
		put("0145", "광운대");
		put("0042", "광주");
		put("0036", "광주송정");
		put("0082", "광천");
		put("0241", "괴목");
		put("0050", "구례구");
		put("0013", "구미");
		put("0019", "구포");
		put("0329", "구학");
		put("0323", "국수");
		put("0061", "군북");
		put("0505", "군산");
		put("0043", "극락강");
		put("0736", "금강");
		put("0239", "금곡");
		put("0732", "금릉");
		put("0187", "기장");
		put("0246", "김유정");
		put("0031", "김제");
		put("0012", "김천");
		put("0507", "김천구미");
		put("0461", "나원");
		put("0201", "나전");
		put("0037", "나주");
		put("0164", "나한정");
		put("0452", "남문구");
		put("0131", "남문산");
		put("0317", "남성현");
		put("0048", "남원");
		put("0186", "남창");
		put("0152", "남춘천");
		put("0497", "남평");
		put("0361", "노안");
		put("0027", "논산");
		put("0391", "능곡");
		put("0132", "능주");
		put("0266", "다시");
		put("0176", "단성");
		put("0096", "단양");
		put("0247", "달천");
		put("0417", "대광리");
		put("0023", "대구");
		put("0148", "대성리");
		put("0310", "대신");
		put("0430", "대야");
		put("0010", "대전");
		put("0083", "대천");
		put("0233", "덕산");
		put("0168", "덕소");
		put("0052", "덕양");
		put("0209", "덕하");
		put("0111", "도계");
		put("0077", "도고온천");
		put("0095", "도담");
		put("0403", "도라산");
		put("0015", "동대구");
		put("0410", "동두천");
		put("0189", "동래");
		put("0450", "동백산");
		put("0366", "동산");
		put("0364", "동익산");
		put("0437", "동점");
		put("0113", "동해");
		put("0173", "동화");
		put("0615", "두정");
		put("0205", "득량");
		put("0059", "마산");
		put("0147", "마석");
		put("0038", "망상");
		put("0249", "매곡");
		put("0235", "명봉");
		put("0041", "목포");
		put("0074", "목행");
		put("0229", "몽탄");
		put("0236", "무안");
		put("0114", "묵호");
		put("0401", "문산");
		put("0224", "물금");
		put("0244", "미평");
		put("0120", "민둥산");
		put("0017", "밀양");
		put("0062", "반성");
		put("0738", "백마고지");
		put("0167", "백산");
		put("0258", "백양리");
		put("0034", "백양사");
		put("0089", "벌교");
		put("0451", "범일");
		put("0198", "별어곡");
		put("0069", "보성");
		put("0434", "봉성");
		put("0175", "봉양");
		put("0105", "봉화");
		put("0008", "부강");
		put("0020", "부산");
		put("0190", "부전");
		put("0464", "부조");
		put("0807", "부천");
		put("0222", "북영천");
		put("0064", "북천");
		put("0166", "분천");
		put("0185", "불국사");
		put("0312", "사곡");
		put("0255", "사릉");
		put("0193", "사방");
		put("0121", "사북");
		put("0143", "사상");
		put("0018", "삼랑진");
		put("0044", "삼례");
		put("0250", "삼산");
		put("0213", "삼탄");
		put("0080", "삽교");
		put("0272", "상동");
		put("0635", "상봉");
		put("0156", "상주");
		put("0257", "상천");
		put("0341", "서경주");
		put("0275", "서광주");
		put("0025", "서대전");
		put("0833", "서빙고");
		put("0001", "서울");
		put("0243", "서정리");
		put("0086", "서천");
		put("0325", "석불");
		put("0108", "석포");
		put("0199", "선평");
		put("0248", "성환");
		put("0411", "소요산");
		put("0142", "소정리");
		put("0188", "송정");
		put("0455", "수영");
		put("0003", "수원");
		put("0051", "순천");
		put("0161", "승부");
		put("0508", "신경주");
		put("0263", "신기");
		put("0182", "신녕");
		put("0223", "신동");
		put("0078", "신례원");
		put("0369", "신리");
		put("0174", "신림");
		put("0416", "신망리");
		put("0281", "신창");
		put("0465", "신창원");
		put("0265", "신탄리");
		put("0009", "신탄진");
		put("0032", "신태인");
		put("0245", "심천");
		put("0116", "쌍용");
		put("0503", "아산");
		put("0324", "아신");
		put("0202", "아우라지");
		put("0311", "아포");
		put("0192", "안강");
		put("0100", "안동");
		put("0135", "안양");
		put("0230", "약목");
		put("0171", "양동");
		put("0486", "양보");
		put("0269", "양수");
		put("0731", "양원");
		put("0463", "양자동");
		put("0091", "양평");
		put("0053", "여수EXPO");
		put("0139", "여천");
		put("0195", "연당");
		put("0220", "연무대");
		put("0026", "연산");
		put("0415", "연천");
		put("0011", "영동");
		put("0002", "영등포");
		put("0117", "영월");
		put("0098", "영주");
		put("0103", "영천");
		put("0075", "예당");
		put("0119", "예미");
		put("0079", "예산");
		put("0162", "예천");
		put("0134", "오근장");
		put("0141", "오산");
		put("0297", "오송");
		put("0047", "오수");
		put("0067", "옥곡");
		put("0154", "옥산");
		put("0892", "옥수");
		put("0022", "옥천");
		put("0076", "온양온천");
		put("0484", "완사");
		put("0836", "왕십리");
		put("0014", "왜관");
		put("0618", "외고산");
		put("0159", "용궁");
		put("0347", "용동");
		put("0169", "용문");
		put("0104", "용산");
		put("0733", "운천");
		put("0509", "울산");
		put("0084", "웅천");
		put("0215", "원동");
		put("0479", "원북");
		put("0092", "원주");
		put("0217", "월내");
		put("0383", "율촌");
		put("0072", "음성");
		put("0101", "의성");
		put("0264", "의정부");
		put("0055", "이양");
		put("0300", "이원");
		put("0030", "익산");
		put("0921", "인천공항");
		put("0227", "일광");
		put("0040", "일로");
		put("0395", "일산");
		put("0204", "일신");
		put("0165", "임기");
		put("0362", "임성리");
		put("0046", "임실");
		put("0402", "임진강");
		put("0212", "입실");
		put("0197", "자미원");
		put("0446", "장락");
		put("0035", "장성");
		put("0504", "장항");
		put("0454", "재송");
		put("0414", "전곡");
		put("0006", "전의");
		put("0045", "전주");
		put("0158", "점촌");
		put("0262", "정동진");
		put("0200", "정선");
		put("0033", "정읍");
		put("0093", "제천");
		put("0267", "제천순환");
		put("0088", "조성");
		put("0007", "조치원");
		put("0126", "좌천");
		put("0138", "주덕");
		put("0815", "주안");
		put("0234", "중리");
		put("0071", "증평");
		put("0308", "지탄");
		put("0170", "지평");
		put("0511", "진례");
		put("0066", "진상");
		put("0480", "진성");
		put("0056", "진영");
		put("0063", "진주");
		put("0278", "진주수목원");
		put("0140", "진해");
		put("0057", "창원");
		put("0512", "창원중앙");
		put("0751", "천마산");
		put("0005", "천안");
		put("0502", "천안아산");
		put("0109", "철암");
		put("0016", "청도");
		put("0090", "청량리");
		put("0155", "청리");
		put("0253", "청소");
		put("0070", "청주");
		put("0276", "청주공항");
		put("0149", "청평");
		put("0412", "초성리");
		put("0449", "추전");
		put("0133", "추풍령");
		put("0106", "춘양");
		put("0153", "춘천");
		put("0073", "충주");
		put("0396", "탄현");
		put("0102", "탑리");
		put("0714", "태금");
		put("0123", "태백");
		put("0125", "태화강");
		put("0110", "통리");
		put("0146", "퇴계원");
		put("0400", "파주");
		put("0085", "판교");
		put("0256", "평내호평");
		put("0130", "평촌");
		put("0004", "평택");
		put("0058", "포항");
		put("0097", "풍기");
		put("0065", "하동");
		put("0238", "하양");
		put("0129", "한림정");
		put("0413", "한탄강");
		put("0196", "함백");
		put("0060", "함안");
		put("0029", "함열");
		put("0157", "함창");
		put("0039", "함평");
		put("0127", "해운대");
		put("0390", "행신");
		put("0107", "현동");
		put("0211", "호계");
		put("0081", "홍성");
		put("0210", "화명");
		put("0183", "화본");
		put("0054", "화순");
		put("0128", "황간");
		put("0136", "횡천");
		put("0458", "효문");
		put("0191", "효자");
		put("0274", "효천");
		put("0343", "흑석리");
		put("0178", "희방사");
	}};
	
	Map<String, String> name_number_stations = new HashMap<String, String>() {{
		put("가수원", "0342");
		put("가야", "0476");
		put("가평", "0150");
		put("각계", "0309");
		put("간현", "0172");
		put("갈촌", "0481");
		put("강경", "0028");
		put("강릉", "0115");
		put("강촌", "0151");
		put("개양", "0482");
		put("개태사", "0219");
		put("개포", "0160");
		put("거제", "0216");
		put("거촌", "0433");
		put("건천", "0184");
		put("경강", "0208");
		put("경산", "0024");
		put("경주", "0021");
		put("경화", "0468");
		put("계룡", "0218");
		put("고막원", "0240");
		put("고한", "0122");
		put("곡성", "0049");
		put("공전", "0259");
		put("관촌", "0370");
		put("광곡", "0491");
		put("광명", "0501");
		put("광양", "0068");
		put("광운대", "0145");
		put("광주", "0042");
		put("광주송정", "0036");
		put("광천", "0082");
		put("괴목", "0241");
		put("구례구", "0050");
		put("구미", "0013");
		put("구포", "0019");
		put("구학", "0329");
		put("국수", "0323");
		put("군북", "0061");
		put("군산", "0505");
		put("극락강", "0043");
		put("금강", "0736");
		put("금곡", "0239");
		put("금릉", "0732");
		put("기장", "0187");
		put("김유정", "0246");
		put("김제", "0031");
		put("김천", "0012");
		put("김천구미", "0507");
		put("나원", "0461");
		put("나전", "0201");
		put("나주", "0037");
		put("나한정", "0164");
		put("남문구", "0452");
		put("남문산", "0131");
		put("남성현", "0317");
		put("남원", "0048");
		put("남창", "0186");
		put("남춘천", "0152");
		put("남평", "0497");
		put("노안", "0361");
		put("논산", "0027");
		put("능곡", "0391");
		put("능주", "0132");
		put("다시", "0266");
		put("단성", "0176");
		put("단양", "0096");
		put("달천", "0247");
		put("대광리", "0417");
		put("대구", "0023");
		put("대성리", "0148");
		put("대신", "0310");
		put("대야", "0430");
		put("대전", "0010");
		put("대천", "0083");
		put("덕산", "0233");
		put("덕소", "0168");
		put("덕양", "0052");
		put("덕하", "0209");
		put("도계", "0111");
		put("도고온천", "0077");
		put("도담", "0095");
		put("도라산", "0403");
		put("동대구", "0015");
		put("동두천", "0410");
		put("동래", "0189");
		put("동백산", "0450");
		put("동산", "0366");
		put("동익산", "0364");
		put("동점", "0437");
		put("동해", "0113");
		put("동화", "0173");
		put("두정", "0615");
		put("득량", "0205");
		put("마산", "0059");
		put("마석", "0147");
		put("망상", "0038");
		put("매곡", "0249");
		put("명봉", "0235");
		put("목포", "0041");
		put("목행", "0074");
		put("몽탄", "0229");
		put("무안", "0236");
		put("묵호", "0114");
		put("문산", "0401");
		put("물금", "0224");
		put("미평", "0244");
		put("민둥산", "0120");
		put("밀양", "0017");
		put("반성", "0062");
		put("백마고지", "0738");
		put("백산", "0167");
		put("백양리", "0258");
		put("백양사", "0034");
		put("벌교", "0089");
		put("범일", "0451");
		put("별어곡", "0198");
		put("보성", "0069");
		put("봉성", "0434");
		put("봉양", "0175");
		put("봉화", "0105");
		put("부강", "0008");
		put("부산", "0020");
		put("부전", "0190");
		put("부조", "0464");
		put("부천", "0807");
		put("북영천", "0222");
		put("북천", "0064");
		put("분천", "0166");
		put("불국사", "0185");
		put("사곡", "0312");
		put("사릉", "0255");
		put("사방", "0193");
		put("사북", "0121");
		put("사상", "0143");
		put("삼랑진", "0018");
		put("삼례", "0044");
		put("삼산", "0250");
		put("삼탄", "0213");
		put("삽교", "0080");
		put("상동", "0272");
		put("상봉", "0635");
		put("상주", "0156");
		put("상천", "0257");
		put("서경주", "0341");
		put("서광주", "0275");
		put("서대전", "0025");
		put("서빙고", "0833");
		put("서울", "0001");
		put("서정리", "0243");
		put("서천", "0086");
		put("석불", "0325");
		put("석포", "0108");
		put("선평", "0199");
		put("성환", "0248");
		put("소요산", "0411");
		put("소정리", "0142");
		put("송정", "0188");
		put("수영", "0455");
		put("수원", "0003");
		put("순천", "0051");
		put("승부", "0161");
		put("신경주", "0508");
		put("신기", "0263");
		put("신녕", "0182");
		put("신동", "0223");
		put("신례원", "0078");
		put("신리", "0369");
		put("신림", "0174");
		put("신망리", "0416");
		put("신창", "0281");
		put("신창원", "0465");
		put("신탄리", "0265");
		put("신탄진", "0009");
		put("신태인", "0032");
		put("심천", "0245");
		put("쌍용", "0116");
		put("아산", "0503");
		put("아신", "0324");
		put("아우라지", "0202");
		put("아포", "0311");
		put("안강", "0192");
		put("안동", "0100");
		put("안양", "0135");
		put("약목", "0230");
		put("양동", "0171");
		put("양보", "0486");
		put("양수", "0269");
		put("양원", "0731");
		put("양자동", "0463");
		put("양평", "0091");
		put("여수EXPO", "0053");
		put("여천", "0139");
		put("연당", "0195");
		put("연무대", "0220");
		put("연산", "0026");
		put("연천", "0415");
		put("영동", "0011");
		put("영등포", "0002");
		put("영월", "0117");
		put("영주", "0098");
		put("영천", "0103");
		put("예당", "0075");
		put("예미", "0119");
		put("예산", "0079");
		put("예천", "0162");
		put("오근장", "0134");
		put("오산", "0141");
		put("오송", "0297");
		put("오수", "0047");
		put("옥곡", "0067");
		put("옥산", "0154");
		put("옥수", "0892");
		put("옥천", "0022");
		put("온양온천", "0076");
		put("완사", "0484");
		put("왕십리", "0836");
		put("왜관", "0014");
		put("외고산", "0618");
		put("용궁", "0159");
		put("용동", "0347");
		put("용문", "0169");
		put("용산", "0104");
		put("운천", "0733");
		put("울산", "0509");
		put("웅천", "0084");
		put("원동", "0215");
		put("원북", "0479");
		put("원주", "0092");
		put("월내", "0217");
		put("율촌", "0383");
		put("음성", "0072");
		put("의성", "0101");
		put("의정부", "0264");
		put("이양", "0055");
		put("이원", "0300");
		put("익산", "0030");
		put("인천공항", "0921");
		put("일광", "0227");
		put("일로", "0040");
		put("일산", "0395");
		put("일신", "0204");
		put("임기", "0165");
		put("임성리", "0362");
		put("임실", "0046");
		put("임진강", "0402");
		put("입실", "0212");
		put("자미원", "0197");
		put("장락", "0446");
		put("장성", "0035");
		put("장항", "0504");
		put("재송", "0454");
		put("전곡", "0414");
		put("전의", "0006");
		put("전주", "0045");
		put("점촌", "0158");
		put("정동진", "0262");
		put("정선", "0200");
		put("정읍", "0033");
		put("제천", "0093");
		put("제천순환", "0267");
		put("조성", "0088");
		put("조치원", "0007");
		put("좌천", "0126");
		put("주덕", "0138");
		put("주안", "0815");
		put("중리", "0234");
		put("증평", "0071");
		put("지탄", "0308");
		put("지평", "0170");
		put("진례", "0511");
		put("진상", "0066");
		put("진성", "0480");
		put("진영", "0056");
		put("진주", "0063");
		put("진주수목원", "0278");
		put("진해", "0140");
		put("창원", "0057");
		put("창원중앙", "0512");
		put("천마산", "0751");
		put("천안", "0005");
		put("천안아산", "0502");
		put("철암", "0109");
		put("청도", "0016");
		put("청량리", "0090");
		put("청리", "0155");
		put("청소", "0253");
		put("청주", "0070");
		put("청주공항", "0276");
		put("청평", "0149");
		put("초성리", "0412");
		put("추전", "0449");
		put("추풍령", "0133");
		put("춘양", "0106");
		put("춘천", "0153");
		put("충주", "0073");
		put("탄현", "0396");
		put("탑리", "0102");
		put("태금", "0714");
		put("태백", "0123");
		put("태화강", "0125");
		put("통리", "0110");
		put("퇴계원", "0146");
		put("파주", "0400");
		put("판교", "0085");
		put("평내호평", "0256");
		put("평촌", "0130");
		put("평택", "0004");
		put("포항", "0058");
		put("풍기", "0097");
		put("하동", "0065");
		put("하양", "0238");
		put("한림정", "0129");
		put("한탄강", "0413");
		put("함백", "0196");
		put("함안", "0060");
		put("함열", "0029");
		put("함창", "0157");
		put("함평", "0039");
		put("해운대", "0127");
		put("행신", "0390");
		put("현동", "0107");
		put("호계", "0211");
		put("홍성", "0081");
		put("화명", "0210");
		put("화본", "0183");
		put("화순", "0054");
		put("황간", "0128");
		put("횡천", "0136");
		put("효문", "0458");
		put("효자", "0191");
		put("효천", "0274");
		put("흑석리", "0343");
		put("희방사", "0178");
	}};
	
	TreeMap<String, String> t_number_name_stations = new TreeMap<String, String>(number_name_stations);
	TreeMap<String, String> t_name_number_stations = new TreeMap<String, String>(name_number_stations);
}
