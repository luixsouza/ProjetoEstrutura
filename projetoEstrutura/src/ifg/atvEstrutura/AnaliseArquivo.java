package src.ifg.atvEstrutura;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AnaliseArquivo {

    public static void main(String[] args) throws FileNotFoundException {

        List<Item> items = new ArrayList<>();
		Map<String, List<Item>> groupIndex = new HashMap<>();
		Map<String, List<Item>> educationIndex = new HashMap<>();
		
        try {
        	
        	Scanner scanner = new Scanner(new File("C:\\Users\\Luis Eduardo\\Documents\\StudentsPerformance.csv"));
           
            scanner.nextLine();
            while (scanner.hasNext()) {
                items.add(new Item(scanner.nextLine().split(",")));
            }

			for (Item item : items) {
				String group = item.getRace();
				groupIndex.computeIfAbsent(group, k -> new ArrayList<>()).add(item);
			}

			for (Item item : items) {
				String education = item.getParentalLevelEducation();
				groupIndex.computeIfAbsent(education, k -> new ArrayList<>()).add(item);
			}
        	
        	int a = 100;
        	do { 
        		try (Scanner leia = new Scanner(System.in)) {
					System.out.println("-----------------------------------------");
					System.out.println("Menu listagem do arquivo");
					System.out.println("-----------------------------------------");
					System.out.println("1- Ranking de matematica com  menor e maior nota\n" + 
							"2- Ranking de leitura com menor e maior nota\n" + 
							"3- Raking de escrita com menor e maior nota\n" +
							"4- Lista do grupo A\n" + 
							"5- Lista do grupo B\n" + 
							"6- Lista do grupo C\n" +
							"7- Lista do grupo D\n" +
							"8- Lista do grupo E\n" +
							"9- Lista educação dos pais com diploma de bachareal\n" +
							"10- Lista educação dos pais com faculdade\n" + 
							"11- Lista educação dos pais com mestrado\n" +
							"12- Lista educação dos pais com ensino médio\n" +
							"13- Lista de almoço padrão\n" +
							"14- Lista de almoço grátis\n" +
							"15- Lista sem curso de preparação\n" +
							"16- Lista com curso de preparação\n" + 
							"0- Sair");
					System.out.println("-----------------------------------------");
					String buffer = leia.nextLine();
					if(buffer == null) continue;
				
					a = Integer.parseInt(buffer);
				}


        		switch (a) {
        		case 1: 
        		    String score = "math";
                    quickSort(items, 0, items.size() - 1, score);
                    System.out.println("\n-----------------------------------------\n");
                    System.out.println("Ranking de Matematica: ");
                    imprimeArray(items, score); 
                    System.out.println("A menor nota de matematica: " + items.get(0).getMathScore());
                    System.out.println("A maior nota de matematica: " + items.get(items.size()-1).getMathScore());
                    System.out.println("\n-----------------------------------------\n");
                    break;
        		
        		case 2: 
        			score = "read";
                    quickSort(items, 0, items.size() - 1, score);
                    System.out.println("\n-----------------------------------------\n");
                    System.out.println("Ranking de Leitura: ");
                    imprimeArray(items, score); 
                    System.out.println("A menor nota de leitura: " + items.get(0).getReadScore());
                    System.out.println("A maior nota de leitura: " + items.get(items.size()-1).getReadScore());
                    System.out.println("\n-----------------------------------------\n");
                    break;
                    
        		case 3:
        			score = "write";
                    quickSort(items, 0, items.size() - 1, score);
                    System.out.println("\n-----------------------------------------\n");
                    System.out.println("Ranking de Escrita: ");
                    imprimeArray(items, score); 
                    System.out.println("A menor nota de escrita: " + items.get(0).getWriteScore());
                    System.out.println("A maior nota de escrita: " + items.get(items.size()-1).getWriteScore());
                    System.out.println("\n-----------------------------------------\n");
                    break;



				case 4:	String grupo = "group A";
					imprimeTodosItensGrupo(groupIndex, grupo);
					break;
			
                    
        		case 5: grupo = "group B";
					imprimeTodosItensGrupo(groupIndex, grupo);
					break;
		
        			 
        		case 6: grupo = "group C";
					imprimeTodosItensGrupo(groupIndex, grupo);
					break;
        			 
        		case 7: grupo = "group D";
					imprimeTodosItensGrupo(groupIndex, grupo);
					break;
        			
        		case 8:grupo = "group E";
					imprimeTodosItensGrupo(groupIndex, grupo);
					break;
          			 
        		case 9: 
        			String nivelEducacaoPais = "bachelor's degree";
        			imprimeTodosItensNivelEducacaoPais(groupIndex, nivelEducacaoPais );
        			break;
        			
        		case 10:
        			nivelEducacaoPais = "some college";
        			imprimeTodosItensNivelEducacaoPais(groupIndex, nivelEducacaoPais );
        			break;
        			
        		case 11:
        			nivelEducacaoPais = "master's degree";
        			imprimeTodosItensNivelEducacaoPais(groupIndex, nivelEducacaoPais );
        			break;
        			
        		case 12:
        			nivelEducacaoPais = "high school";
        			imprimeTodosItensNivelEducacaoPais(groupIndex, nivelEducacaoPais );
        			break;
        			
        		case 13: 
        			String lunch = "standard";
        			imprimeTodosItenslunch(items,lunch);
        			break;
        			
        		case 14:
        			lunch = "free/reduced";
        			imprimeTodosItenslunch(items,lunch);
        			break;
        			
        		case 15: 
        			String testPreparation = "none";
        			imprimeTodosItensCursoPreparacao(items,testPreparation);
        			break;
        			
        		case 16: 
        			testPreparation = "completed";
        			imprimeTodosItensCursoPreparacao(items,testPreparation);
        			break;
        			
        		case 0:
        			System.out.println("Encerrando o programa");
        			break;
        		default:
        			System.out.println("Opção inválida");
        		}
        		
        	}while(a != 0);
            
            
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("Arquivo não encontrado: " + e.getMessage());
        }
    }
    
	private static void imprimeTodosItensGrupo(Map<String, List<Item>> groupIndex, String grupo) {
		List<Item> itemsDoGrupo = groupIndex.get(grupo);
	
		if (itemsDoGrupo != null) {
			System.out.println("Os itens do " + grupo + ":");
	
			for (Item item : itemsDoGrupo) {
				if (item.getRace().equals(grupo)) {
					System.out.println("Gênero: " + item.getGender() + "\n" +
							"Nível de educação dos pais: " + item.getParentalLevelEducation() + "\n" +
							"Curso de preparação: " + item.getTestPreparation() + "\n" +
							"Almoço: " + item.getLunch() + "\n" +
							"Notas de matemática: " + item.getMathScore() + "\n" +
							"Notas de leitura: " + item.getReadScore() + "\n" +
							"Notas de escrita: " + item.getWriteScore());
					System.out.println("\n-----------------------------------------\n");
				}
			}
		}
	}
	
	private static void imprimeTodosItensNivelEducacaoPais(Map<String, List<Item>> educationIndex, String nivelEducacaoPais) {
		List<Item> itensDoNivelEducacao = educationIndex.get(nivelEducacaoPais);
		
		if (itensDoNivelEducacao != null) {
			System.out.println("Os itens do " + nivelEducacaoPais + ":");
			
			for (Item item : itensDoNivelEducacao) {
				if (item.getParentalLevelEducation().equals(nivelEducacaoPais)) {
					System.out.println("Gênero: " + item.getGender() + "\n" +
							"Raça: " + item.getRace() + "\n" +
							"Curso de preparação: " + item.getTestPreparation() + "\n" +
							"Almoço: " + item.getLunch() + "\n" +
							"Notas de matemática: " + item.getMathScore() + "\n" +
							"Notas de leitura: " + item.getReadScore() + "\n" +
							"Notas de escrita: " + item.getWriteScore());
					System.out.println("\n-----------------------------------------\n");
				}
			}
		}
	}
	
	
    
    private static void imprimeTodosItenslunch(List<Item> items, String lunch) {
    	System.out.println("Os itens de acordo com " + lunch + ":");
    	for (int i = 0; i < items.size(); i++) {
        	if (items.get(i).getLunch().equals(lunch)) {
        		 System.out.println("Genero: " + items.get(i).getGender() + "\n" +
        				 "Raça: " + items.get(i).getRace() +"\n" +
        				"Curso de preparação: " + items.get(i).getTestPreparation() + "\n" +
        				 "Nivel de educação dos pais: " + items.get(i).getParentalLevelEducation() + "\n" + 
        				"Notas matematia: " + items.get(i).getMathScore() + "\n" +
        				 "Notas leitura: " + items.get(i).getReadScore() + "\n" +
        				"Notas escrita: " + items.get(i).getWriteScore() );
        		 System.out.println("\n-----------------------------------------\n");
        	}
        	
           
        }
    }
    
    private static void imprimeTodosItensCursoPreparacao(List<Item> items, String testPreparation) {
    	System.out.println("Os itens de acordo com " + testPreparation + ":");
    	for (int i = 0; i < items.size(); i++) {
        	if (items.get(i).getTestPreparation().equals(testPreparation)) {
        		 System.out.println("Genero: " + items.get(i).getGender() + "\n" +
        				 "Raça: " + items.get(i).getRace() +"\n" +
        				"Almoço: " + items.get(i).getLunch() + "\n" +
        				 "Nivel de educação dos pais: " + items.get(i).getParentalLevelEducation() + "\n" + 
        				"Notas matematia: " + items.get(i).getMathScore() + "\n" +
        				 "Notas leitura: " + items.get(i).getReadScore() + "\n" +
        				"Notas escrita: " + items.get(i).getWriteScore() );
        		 System.out.println("\n-----------------------------------------\n");
        	}
        	
           
        }
    }
    
    private static void quickSort(List<Item> items, int inicio, int termino, String score) {
        if (inicio < termino) {
            int indiceParticao = particiona(items, inicio, termino, score);
            quickSort(items, inicio, indiceParticao - 1, score);
            quickSort(items, indiceParticao + 1, termino, score);
        }
    }

    private static int particiona(List<Item> items, int inicio, int termino, String score) {
        Item pivo = items.get(termino);
        int i = inicio - 1;
        int a = 0;
        int b = 0;
        for (int j = inicio; j < termino; j++) {
        	if (score.equals("math")) {
        		a = Integer.parseInt(items.get(j).getMathScore());
            	b = Integer.parseInt(pivo.getMathScore());
        	}
        	if (score.equals("read")) {
        		a = Integer.parseInt(items.get(j).getReadScore());
            	b = Integer.parseInt(pivo.getReadScore());
        	}
        	if (score.equals("write")) {
        		a = Integer.parseInt(items.get(j).getWriteScore());
            	b = Integer.parseInt(pivo.getWriteScore());
        	}
        	
            if (a <= b) {
                i++;
                troca(items, i, j);
            }
        }

        troca(items, i + 1, termino);
        return i + 1;
    }

    private static void troca(List<Item> items, int i, int j) {
    	Item tempUm = items.get(i);
    	Item tempDois = items.get(j);
    	items.set(i, tempDois);
    	items.set(j, tempUm);
    }

    private static void imprimeArray(List<Item> items, String score) {
        for (int i = 0; i < items.size(); i++) {
        	if (score.equals("math")) {
        		 System.out.println("Math score " + (i + 1) + ": " + items.get(i).getMathScore());
        	}
        	if (score.equals("read")) {
        		 System.out.println("Read score " + (i + 1) + ": " + items.get(i).getReadScore());
        	}
        	if (score.equals("write")) {
        		 System.out.println("Write score " + (i + 1) + ": " + items.get(i).getWriteScore());
        	}
           
        }
    }
   
}
