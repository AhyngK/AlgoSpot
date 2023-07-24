import java.util.*;
import java.io.*;

public class Picnic_01 {
	static int count =0;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		for(int tc=0; tc<testCase; tc++) {
		
			// 1. �л� �Ѹ��� ���� ��� ģ���� �����ϴ� ���, ����Ʈ�� ����
			// �Է� �޴� �κ�
			StringTokenizer st = new StringTokenizer(br.readLine());
			int students = Integer.parseInt(st.nextToken());
			int friends = Integer.parseInt(st.nextToken());
			
			List<List<Integer>> studentFriends = new ArrayList<>();
			for(int i=0; i<students; i++) {
				studentFriends.add(new ArrayList<>());
			}
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<friends; i++) {
				int friend1 = Integer.parseInt(st.nextToken());
				int friend2 = Integer.parseInt(st.nextToken());
				
				studentFriends.get(friend1).add(friend2);
				studentFriends.get(friend2).add(friend1);
			}
			
			boolean[] check = new boolean[students];
			Arrays.fill(check, false);
			count = 0;
			
			oneStudentChoose(0,studentFriends,check);
			System.out.println(count);
		}
	}
	
	static void oneStudentChoose(int index, List<List<Integer>> studentFriends, boolean[] check) {
		// ���� ���
		//1. �� ������ ������� ������ �Ϸ��� ��� (��� ����)
		if(index==check.length) {
			count++;
			return;
		}
		// 2. ���� ����� ģ���� ���� ��� (����Ǵ� ���, �̷� ��찡 ������??)
		if(studentFriends.get(index).size()==0) {
			return;
		}
		
		// ������ �κ�
		// ���� ����� �̹� ������ ��� : ���� ������� �Ѿ�� �Ѵ�
		if(check[index]) {
			oneStudentChoose(index+1,studentFriends, check);
		}
		// ���� �������� ���� ��� : �� ����� ���� ¦�� �� �� �ִ� ����� �����Ѵ�
		else {
			for(int i=0; i<studentFriends.get(index).size(); i++) {
				// ¦���� ������ ��� �� �̹� �Ҵ�Ǿ����� ���� Ȯ�� �� �Ҵ�
				if(!check[studentFriends.get(index).get(i)]) {
					check[index] = true;
					check[studentFriends.get(index).get(i)] = true;
					oneStudentChoose(index+1, studentFriends, check);
					check[index] = false;
					check[studentFriends.get(index).get(i)] = false;
				}
			}
		}
	}
	

}