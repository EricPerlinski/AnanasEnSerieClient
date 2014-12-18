package model;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		CheckBoxQuestion q = new CheckBoxQuestion();
		q.setName("yooo");
		q.addItem("item1");
		q.addItem("item2");
		q.addItem("item3");
		q.addItem("item4");
		q.addItem("item5");
		
		q.addSelected(1);
		q.addSelected(3);
		q.addSelected(20);
		
		
	
		
		@SuppressWarnings("unused")
		String json = "{\"name\" : \"yooo\" , \"items\" : [\"item1\" , \"item2\" , \"item3\" , \"item4\" , \"item5\"] , \"selected\" : [\"1\" , \"3\" , \"20\"]}";

		
	
		
		RadioButtonQuestion q3 = new RadioButtonQuestion();
		q3.setName("yooo");
		q3.addItem("item1");
		q3.addItem("item2");
		q3.addItem("item3");
		q3.addItem("item4");
		q3.addItem("item5");
		
		q3.setSelected(3);
		
	
		@SuppressWarnings("unused")
		String json2 = "{\"name\" : \"yooo\" , \"items\" : [\"item1\" , \"item2\" , \"item3\" , \"item4\" , \"item5\"] , \"selected\" : \"3\"}";

		
		
		OpenQuestion lq = new OpenQuestion();
		lq.setQuestion("q1");
		lq.setName("libreQuestion");
		
		
		
		
		
		
		Survey s = new Survey();
		s.addQuestion(lq);
		s.addQuestion(q3);
		s.addQuestion(q);
		s.setTitre("yolo");
		
		System.out.println(s.toJson());
		
		System.out.println("---------------------------");
		
		Redirect r = new Redirect();
		r.setTitre("yolo");
		r.setUrl("toto/fzecez.php");
		
		System.out.println(r.toJson());
		
		System.out.println("---------------------------");
		
		Like l = new Like();

		l.setTitre("yolo");
		System.out.println(l.toJson());
		
	}

}
