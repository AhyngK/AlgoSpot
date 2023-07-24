import java.util.*;
import java.io.*;

public class Picnic_01 {
	static int count =0;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		for(int tc=0; tc<testCase; tc++) {
		
			// 1. 학생 한명에 대해 모든 친구를 선택하는 방식, 리스트로 저장
			// 입력 받는 부분
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
		// 기저 사례
		//1. 맨 마지막 사람까지 선택을 완료한 경우 (모두 선택)
		if(index==check.length) {
			count++;
			return;
		}
		// 2. 현재 사람이 친구가 없는 경우 (종료되는 경우, 이런 경우가 있을까??)
		if(studentFriends.get(index).size()==0) {
			return;
		}
		
		// 수행할 부분
		// 현재 사람이 이미 선택한 경우 : 다음 사람으로 넘어가야 한다
		if(check[index]) {
			oneStudentChoose(index+1,studentFriends, check);
		}
		// 아직 선택하지 않은 경우 : 각 사람에 대해 짝이 될 수 있는 사람을 선택한다
		else {
			for(int i=0; i<studentFriends.get(index).size(); i++) {
				// 짝으로 가능한 사람 중 이미 할당되었는지 여부 확인 후 할당
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
