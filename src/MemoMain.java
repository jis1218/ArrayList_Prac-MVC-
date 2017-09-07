import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * ����� System.out.print�� �� �Ѵ�.
 * �Է��� Scanner�� �̿��Ѵ�.
 * 
 * 
 * Controller�� create �Լ��� return ���� Memo�� �����ν� �Լ��� ������ ��������� ������, �׸��� View���� create�� �����.
 * 
 * ��Ʈ�ѷ����� �̷��� �Ѵ�.
 * memo = View.create()
 * Model.create(memo)
 * 
 * �̰� MVC�� ������ �ڵ���, MVC ����, MVC ��Ű���Ķ� �Ҹ�, MVP 
 * 
 * 
 */
public class MemoMain {
	
	Model model = new Model();
	View view = new View();
	
	
	

	public static void main(String[] args) {
		//�Է��� �޾Ƽ� ó���ϴ� ����
		Scanner sc = new Scanner(System.in);
		MemoMain memoMain = new MemoMain(); 
		
		ArrayList<Memo> list = new ArrayList<>();
		
		//��ɾ �Է¹޾Ƽ� �ļ�ó��
		//c- create : ������ �Է� ���� ��ȯ
		//r- read   : ������ �б� ���� ��ȯ
		//u- update : ������ �������
		//d- delete : ������ �������
		String command = "";
		
		
		
		
		while(!command.equals("exit")){
			
			Memo memo = new Memo();
			
			memoMain.view.println("--------��ɾ �Է��ϼ���----------");
			memoMain.view.println("c : ����, r : �б�, u : ����, d : ����, ��: ���");
			memoMain.view.println("exit : ����");
			memoMain.view.println("-------------------------------");
			
			command = sc.nextLine(); // Ű���� �Է� �߿� EnterŰ�� �Էµ� ������ ���
			
			switch(command){
			case "c":
				memoMain.view.create(sc, memoMain.model.create(memo, list));
				break;
				
				//�޸� �����Ϳ� ���� ������ �ʿ��� ��� ��� ��Ʈ�ѷ����� �۾��Ѵ�.
				
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
	
	

	//Ű���� �Է��� �޴� �Լ� 
	//1. �̸� �Է�
	//2. ���� �Է�


}

//�����͸� �����ϴ� ����Ҹ� �����ϴ� ��ü
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
		view.println("�� ��ȣ�� �Է��ϼ���");
		
		String tempNo = sc.nextLine();
		int no = Integer.parseInt(tempNo);
		
		for(Memo memo : list){ //�ϳ��� ���� �� memo���� ArrayList�� �ִ� ��ü�� �ּҰ� ����.
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

//ȭ�� ������� �����ϴ� ��ü
class View{
	
	public Memo create(Scanner sc, Memo memo){
			
		//�۹�ȣ
				
		println("�̸��� �Է��ϼ��� : ");
		memo.name = sc.nextLine();
		println("������ �Է��ϼ��� : ");
		memo.content = sc.nextLine();
		
		//��¥
		memo.datetime = System.currentTimeMillis();
		
		//model.create(memo);
		
		return memo;
		
	}
	
	public void showList(ArrayList<Memo> list) {
		// ArrayList ����Ҹ� �ݺ����� ���鼭 ���پ� ���
		
		for(Memo memo : list){
			print("  No : " + memo.no);
			print("  Author : " + memo.name);
			println("  Content : " + memo.content);
			
		}
		
	}
	
	public void read(Scanner sc, ArrayList<Memo> list){
		System.out.println("�� ��ȣ�� �Է��Ͻÿ� : ");
		
		String tempNo = sc.nextLine();
		int no = Integer.parseInt(tempNo);
		
		for(Memo memo : list){
			if(memo.no == no){ 
				println(" No : " + memo.no);
				println(" Author : " + memo.name);
				println(" Content : " + memo.content);
				
				//���ڷ� �Է¹��� ��¥�� ���� ��¥�� �������ִ� ��
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				
				String formattedDate = sdf.format(memo.datetime);
				
				println(formattedDate);
			}
		}
	}
	
	public int findNoForUpdate(Scanner sc, ArrayList<Memo> list){
		System.out.println("�� ��ȣ�� �Է��Ͻÿ� : ");
		String tempNo = sc.nextLine();
		return Integer.parseInt(tempNo);

	}
	
	public void screenModifed(Scanner sc, Memo memo){
		println("���ο� �̸��� �Է��Ͻÿ� : ");
		memo.name = sc.nextLine();
		println("���ο� ������ �Է��Ͻÿ� : ");
		memo.content = sc.nextLine();
		
		
	}
	
	/*public void update(Scanner sc, ArrayList<Memo> list){
		
		
		System.out.println("�� ��ȣ�� �Է��Ͻÿ� : ");
		
		String tempNo = sc.nextLine();
		int no = Integer.parseInt(tempNo);
		//int index=0;
		for(Memo memo : list){
			if(memo.no == no){ 
				view.println(" No : " + memo.no);
				view.println(" Author : " + memo.name);
				view.println(" Content : " + memo.content);
				//index = list.indexOf(memo);
				
				
				view.println("�̸��� �Է��ϼ��� : ");
				memo.name = sc.nextLine();
				view.println("������ �Է��ϼ��� : ");
				memo.content = sc.nextLine();
				
				
				//���ڷ� �Է¹��� ��¥�� ���� ��¥�� �������ִ� ��
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				
				String formattedDate = sdf.format(memo.datetime);
				
				view.println(formattedDate);
			}
		}
		
		
		//�̹� ������� �ִ� �޸� ��ü�� �ִ� ���̱� ������ set�� �� �ʿ䰡 ����
//		System.out.println(index);
//		memo1.no = no;
//		System.out.println("�ٲ� �̸��� �Է��Ͻÿ� : ");
//		memo1.name = sc.nextLine();
//		System.out.println("�ٲ� ������ �Է��ϼ��� : ");
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

//���� �� �Ѱ� �Ѱ��� �����ϴ� Ŭ����
class Memo{
	int no;
	String name;
	String content;
	long datetime;
	
}
