package corona;
import java.io.*;
import java.util.Scanner;
public class largeData {
		public static void LargeData(String[] args) { 
			int[][] indat = new int[2880][25];
			int[] seoul = new int[80];
			int[][] detail = new int[80][25];
			int[][] value = new int[80][25];
			//CSV ������ �а� ������ �迭 ���� , arraylist�� ���� ���� �ٸ� ���� �����ص� ������� 
			try { // csv ������ ����
				File csv = new File("C:\\Users\\buleb\\Documents\\카카오톡 받은 파일\\Hello.CSV");
				BufferedReader br = new BufferedReader(new FileReader(csv));
				String line = ""; 
				int row =0 ,i, j;
				while ((line = br.readLine()) != null) { 
					// -1 �ɼ��� ������ "," ���� �� ���鵵 �б� ���� �ɼ� 
					String[] token = line.split(",", -1);
					for(i=0;i<25;i++) { 
						indat[row][i] = Integer.parseInt(token[i]);
						} 
					
					// CSV���� �о� �迭�� �ű� �ڷ� Ȯ���ϱ� ���� ��� 

					row++;
					}
				
				for(row=0;row<80;row++) {
					for(i=0;i<25;i++) {
						for(j=0;j<4;j++) {
							seoul[row] += indat[row+j][i];
							detail[row][i] +=indat[row+j][i];
						}
					}
					

				}
				for(row=0;row<80;row++) {
					for(i=0;i<25;i++) {
						if(seoul[row]>20)
							value[row][i]++;
						if(seoul[row]>40)
							value[row][i]++;
						if(seoul[row]>60)
							value[row][i]++;
						if(detail[row][i]>0)
							value[row][i]++;
						if(detail[row][i]>6)
							value[row][i]++;
						if(detail[row][i]>11)
							value[row][i]++;
						if(detail[row][i]>16)
							value[row][i]++;
						
					}
				}

				System.out.println(""); 
				for(row=0;row<80;row++) {
					for(i=0;i<25;i++) {
						System.out.print(value[row][i] + " "); 
					} 
					System.out.println(""); 
				}
				
	            System.out.println("ã����� ��ġ�� �Է��Ͻÿ�: ");

	            Scanner input = new Scanner(System.in);

	            int num1 = input.nextInt();
	            
	            for(i=0;i<4;i++) {
	            	if(value[i][num1]>5)
	            		 System.out.println("�ſ� �ſ� ����");
	            	else if(value[i][num1]>3)
	           		 System.out.println("�ſ� ����");
	            	else if(value[i][num1]>2)
	           		 System.out.println("����");
	            	else if(value[i][num1]>1)
	           		 System.out.println("���� ����");
	            	else if(value[i][num1]>0)
	              		 System.out.println("����");
	            	else 
	              		 System.out.println("����");
	            }
				
				
				br.close(); 
				} 
			catch (FileNotFoundException e) { 
				e.printStackTrace(); 
				} 
			catch (IOException e) {
				e.printStackTrace();
				}
			}
				
		}
