import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * 출력은 System.out.print로 다 한다.
 * 입력은 Scanner를 이용한다.
 * 
 * 
 * Controller의 create 함수의 return 값을 Memo로 함으로써 함수의 역할을 입출력으로 제한함, 그리고 View에도 create를 만든다.
 * 
 * 콘트롤러에서 이렇게 한다.
 * memo = View.create()
 * Model.create(memo)
 * 
 * 이게 MVC를 따르는 코딩임, MVC 패턴, MVC 아키텍쳐라 불림, MVP 
 * 
 * 
 */
public class MemoMain {
	
	Model model = new Model();
	View view = new View();
	
	
	

	public static void main(String[] args) {
		//입력을 받아서 처리하는 도구
		Scanner sc = new Scanner(System.in);
		MemoMain memoMain = new MemoMain(); 
		
		ArrayList<Memo> list = new ArrayList<>();
		
		//명령어를 입력받아서 후속처리
		//c- create : 데이터 입력 모드로 전환
		//r- read   : 데이터 읽기 모드로 전환
		//u- update : 데이터 수정모드
		//d- delete : 데이터 삭제모드
		String command = "";
		
		
		
		
		while(!command.equals("exit")){
			
			Memo memo = new Memo();
			
			memoMain.view.println("--------명령어를 입력하세요----------");
			memoMain.view.println("c : 쓰기, r : 읽기, u : 수정, d : 삭제, ㅣ: 목록");
			memoMain.view.println("exit : 종료");
			memoMain.view.println("-------------------------------");
			
			command = sc.nextLine(); // 키보드 입력 중에 Enter키가 입력될 때까지 대기
			
			switch(command){
			case "c":
				memoMain.view.create(sc, memoMain.model.create(memo, list));
				break;
				
				//메모 데이터에 대한 조작이 필요할 경우 모두 컨트롤러에서 작업한다.
				
			case "r":
				memoMain.view.read(sc, list);
				break;
			case "u":
				//memoMain.model.update(sc, list);
				int a = memoMain.view.findNoForUpdate(sc, list);
				memoMain.view.screenModifed(sc, memoMain.model.update(a, list));
				
				break;
				
			case "d":
				memoMain.model.delete(sc, list);
				break;
				
			case "l":
				memoMain.view.showList(list);
				break;
				
			
			}
			
		}

	}
	
	

	//키보드 입력을 받는 함수 
	//1. 이름 입력
	//2. 내용 입력


}

//데이터를 저장하는 저장소를 관리하는 객체
class Model{
	
	View view = new View();
	//int lastIndex = 0;
	
	public Memo create(Memo memo, ArrayList<Memo> list){
		
		memo.no = list.size()+1;
	
		list.add(memo);
		
		return memo;
		
	}
	
	public void numUpdate(ArrayList<Memo> list){
		
		for(Memo memo : list){
			
			memo.no = list.indexOf(memo)+1;
			
		}
		
	}
	
	public void delete(Scanner sc, ArrayList<Memo> list){
		view.println("글 번호를 입력하세요");
		
		String tempNo = sc.nextLine();
		int no = Integer.parseInt(tempNo);
		
		for(Memo memo : list){ //하나씩 꺼낼 때 memo에는 ArrayList에 있는 객체의 주소가 들어간다.
			if(memo.no == no){
				list.remove(memo);
				break;
			}
		}	
			
		numUpdate(list);
	}
	
	public Memo update(int indexNo, ArrayList<Memo> list){
		
		for(Memo memo : list){
			
			if(memo.no == indexNo){
				
				return memo;
				
			}
			
		}
		
		return null;
		
	}
	

	
	
	
}

//화면 입출력을 관리하는 객체
class View{
	
	public Memo create(Scanner sc, Memo memo){
			
		//글번호
				
		println("이름을 입력하세요 : ");
		memo.name = sc.nextLine();
		println("내용을 입력하세요 : ");
		memo.content = sc.nextLine();
		
		//날짜
		memo.datetime = System.currentTimeMillis();
		
		//model.create(memo);
		
		return memo;
		
	}
	
	public void showList(ArrayList<Memo> list) {
		// ArrayList 저장소를 반복문을 돌면서 한줄씩 출력
		
		for(Memo memo : list){
			print("  No : " + memo.no);
			print("  Author : " + memo.name);
			println("  Content : " + memo.content);
			
		}
		
	}
	
	public void read(Scanner sc, ArrayList<Memo> list){
		System.out.println("글 번호를 입력하시오 : ");
		
		String tempNo = sc.nextLine();
		int no = Integer.parseInt(tempNo);
		
		for(Memo memo : list){
			if(memo.no == no){ 
				println(" No : " + memo.no);
				println(" Author : " + memo.name);
				println(" Content : " + memo.content);
				
				//숫자로 입력받은 날짜를 실제 날짜로 변경해주는 것
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				
				String formattedDate = sdf.format(memo.datetime);
				
				println(formattedDate);
			}
		}
	}
	
	public int findNoForUpdate(Scanner sc, ArrayList<Memo> list){
		System.out.println("글 번호를 입력하시오 : ");
		String tempNo = sc.nextLine();
		return Integer.parseInt(tempNo);

	}
	
	public void screenModifed(Scanner sc, Memo memo){
		println("새로운 이름을 입력하시오 : ");
		memo.name = sc.nextLine();
		println("새로운 내용을 입력하시오 : ");
		memo.content = sc.nextLine();
		
		
	}
	
	/*public void update(Scanner sc, ArrayList<Memo> list){
		
		
		System.out.println("글 번호를 입력하시오 : ");
		
		String tempNo = sc.nextLine();
		int no = Integer.parseInt(tempNo);
		//int index=0;
		for(Memo memo : list){
			if(memo.no == no){ 
				view.println(" No : " + memo.no);
				view.println(" Author : " + memo.name);
				view.println(" Content : " + memo.content);
				//index = list.indexOf(memo);
				
				
				view.println("이름을 입력하세요 : ");
				memo.name = sc.nextLine();
				view.println("내용을 입력하세요 : ");
				memo.content = sc.nextLine();
				
				
				//숫자로 입력받은 날짜를 실제 날짜로 변경해주는 것
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				
				String formattedDate = sdf.format(memo.datetime);
				
				view.println(formattedDate);
			}
		}
		
		
		//이미 만들어져 있는 메모 객체에 넣는 것이기 때문에 set을 할 필요가 없음
//		System.out.println(index);
//		memo1.no = no;
//		System.out.println("바꿀 이름을 입력하시오 : ");
//		memo1.name = sc.nextLine();
//		System.out.println("바꿀 내용을 입력하세요 : ");
//		memo1.content = sc.nextLine();
//		list.set(index, memo1);
		
		
		//numUpdate();
	}*/
	
	public void print(String string){
		System.out.print(string);
	}
	
	public void println(String string){
		System.out.println(string);
	}
	
}

//개별 글 한개 한개를 저장하는 클래스
class Memo{
	int no;
	String name;
	String content;
	long datetime;
	
}
