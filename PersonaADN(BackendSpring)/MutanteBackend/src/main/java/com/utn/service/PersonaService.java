package com.utn.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.utn.dto.PersonaAdnDTO;
import com.utn.entities.AdnEntity;
import com.utn.entities.PersonaEntity;
import com.utn.repository.PersonaRepository;


@Service()
public class PersonaService{

	private PersonaRepository repository;
    private static boolean mutante = false;
	
	public PersonaService(PersonaRepository repository) {
		this.repository=repository;
		
	}
	
	@Transactional
	public PersonaAdnDTO save(PersonaAdnDTO dto)throws Exception{
		
		PersonaEntity entity = new PersonaEntity();
		
		entity.setNombre(dto.getNombre());
		entity.setApellido(dto.getApellido());
		entity.setEdad(dto.getEdad());
		
		dto.setMutant(isMutant(dto.getAdn())); //Seteo en el DTO si es un mutante o no, ya que si lo queria asignar directamente en la entidad no podia.
		
		entity.setMutant(dto.getMutant());
		
		entity.setAdn(ConvertirACadena(dto.getAdn()));	//Lo convierto a cadena para ahorrar registros en la BD
		
		try {
			entity =repository.save(entity);
			dto.setId(entity.getId());
			return dto;
		} catch (Exception e) {
			throw new Exception();
		}

	}
	
	@Transactional
	public List<PersonaAdnDTO>findAll() throws Exception{
		
		List<PersonaEntity> entities= repository.findAll();
		
		List<PersonaAdnDTO> dtos= new ArrayList<>();
		
		try {
			for (PersonaEntity i: entities) {
				PersonaAdnDTO unDto= new PersonaAdnDTO();
				unDto.setId(i.getId());
				unDto.setNombre(i.getNombre());
				unDto.setApellido(i.getApellido());
				unDto.setEdad(i.getEdad());
				
				unDto.setAdn(ConvertirALista(i.getAdn())); //Lo paso como una lista para poder seguirlo como un array
				
				unDto.setMutant(i.getMutant()); 
				
				dtos.add(unDto);
			}
			return dtos;
		} catch (Exception e) {
			throw new Exception();
		}
		
	}
	
	@Transactional
	public PersonaAdnDTO findById(int id)throws Exception{
		
		Optional<PersonaEntity> entityOptional= repository.findById(id);
		PersonaAdnDTO unDto= new PersonaAdnDTO();
		
		try {
			PersonaEntity entity= entityOptional.get();
			
			unDto.setId(entity.getId());
			unDto.setNombre(entity.getNombre());
			unDto.setApellido(entity.getApellido());
			unDto.setEdad(entity.getEdad());
			
			unDto.setAdn(ConvertirALista(entity.getAdn())); //Lo paso como una lista para poder seguirlo como un array
			
			unDto.setMutant(entity.getMutant());
			
			return unDto;
		} catch (Exception e) {
			
			throw new Exception();
		}
		
		
	}
	
	@Transactional
	public PersonaAdnDTO update(int id, PersonaAdnDTO dto)throws Exception{
		
		Optional<PersonaEntity> entityOptional=repository.findById(id);
		
		
		try {
			PersonaEntity entity=entityOptional.get();
			
			entity.setId(id);
			entity.setNombre(dto.getNombre());
			entity.setApellido(dto.getApellido());
			entity.setEdad(dto.getEdad());
			
			dto.setMutant(isMutant(dto.getAdn())); //Al dto le asigno si es un mutante y mas adelante al adn de la entidad lo vuelvo a guardar como cadena
			
			entity.setMutant(dto.getMutant());
			
			entity.setAdn(ConvertirACadena(dto.getAdn()));
			
			repository.save(entity);
			
			dto.setId(entity.getId());
			
			return dto;
			
		} catch (Exception e) {
		
			throw new Exception();
		}
	
	}
	
	@Transactional
	public boolean delete(int id)throws Exception{
		
		try {
			if (repository.existsById(id)) {
				
				repository.deleteById(id);
				return true;
			}else {
				throw new Exception();	
			}
		} catch (Exception e) {
			throw new Exception();	
		}
	}
	
	public static boolean isMutant(List<AdnEntity> dna) {
        
        return RecorrerMatriz(GuardarAChar(dna));
        
    }

    public static char[][] GuardarAChar(List<AdnEntity> dna) {
        char matriz[][] = new char[dna.size()][dna.size()]; //Matriz de valores que traemos.
        
        for (int i = 0; i < dna.size(); i++) {
            for (int j = 0; j < dna.get(0).getCadena().length(); j++) {
                matriz[i][j] = dna.get(i).getCadena().charAt(j);
            }
        }
        
        return matriz;
    }

    public static boolean RecorrerMatriz(char[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            
            if(mutante == true){break;} // si el mutante fue encontrado, se ejecuta el break para acelerar tiempos
                
            for (int j = 0; j < matriz[0].length; j++) {

                if(mutante != true){
                    if ((j < matriz[0].length - 3) && (i < matriz.length - 3)) {  // -3 ya que necesito moverme 3 posiciones para comprobar los caracteres del ADN 
                        mutante = mutanteDiagonal(matriz,i,j); 
                    }

                    if (j < matriz[0].length - 3) {
                        mutante = mutanteFilas(matriz, i, j);
                    }

                    if (i < matriz.length - 3) {
                        mutante = mutanteColumnas(matriz,i,j);
                    }
                }else{break;}
            }
        }
        System.out.println("Salgo como "+mutante);
        return mutante;
        
    }
    
    public static boolean mutanteDiagonal(char matriz[][], int i, int j){
            if ((matriz[i][j] == matriz[i + 1][j + 1])) { 
                if (matriz[i][j] == matriz[i + 2][j + 2]) {
                    if (matriz[i][j] == matriz[i + 3][j + 3]) {
                        mutante=true;
                    }
                }
            }
        return mutante;
    }
    
    public static boolean mutanteColumnas(char matriz[][], int i, int j){
            if ((matriz[i][j] == matriz[i + 1][j])) { 
                if (matriz[i][j] == matriz[i + 2][j]) {
                    if (matriz[i][j] == matriz[i + 3][j]) {
                        mutante=true;
                    }
                }
            }
        return mutante;
    }
    
    public static boolean mutanteFilas(char matriz[][], int i, int j){
            if ((matriz[i][j] == matriz[i][j + 1])) { 
                if (matriz[i][j] == matriz[i][j + 2]) {
                    if (matriz[i][j] == matriz[i][j + 3]) {
                        mutante=true;
                    }
                }
            }
        return mutante;
    }

    public AdnEntity ConvertirACadena(List<AdnEntity> lista) {
    	String cadena = "";
    	
    	for (int i = 0; i < lista.size(); i++) {
    		cadena += lista.get(i).getCadena(); // Junto todo en una cadena la cual luego mando dentro de una nueva entidad
    	}
    	
    	return new AdnEntity(cadena);
    }
    
    public List<AdnEntity> ConvertirALista(AdnEntity adn){
    	
    	List<AdnEntity> lista = new ArrayList<AdnEntity>();
    	
    	AdnEntity pivot;
    	
    	String cadenaRestaurada = "";
    	
    	int numero = (int) Math.sqrt(adn.getCadena().length()); // Cantidad de palabras dentro de el array
    	
    	int iterador = 0; // Iterador lo que va a haces es controlar que se vayan guardando la cantidad de letras
    	
    	for (int i = 0; i < Math.sqrt(adn.getCadena().length()); i++) {  // Necesitamos que cree la misma cantidad de entidades que habian antes de guardarlo
    		
    		pivot = new AdnEntity();
    		
    		for (int j = iterador; j < iterador+numero; j++) {	// El iterador + numero, significa que en esta cadena se guardara la misma cantidad de letras que cantidad de palabras por eso esta limitado a ser una matriz cuadrada, no por el algoritmo sino por una cuestion de no tener que guardar la cantidad de letras ni de palabra en la base de datso
    			
        		cadenaRestaurada += adn.getCadena().charAt(j); 
        		
			}
    		
    		iterador += numero; // El iterador se le suma el numero de palabras para que en la proxima iteracion con el for pueda volver a guardar la cantidad de letras en cada palabra
    		
    		pivot.setCadena(cadenaRestaurada);
    		
    		lista.add(pivot);
    		
    		cadenaRestaurada = ""; // Refresco el string para no acumular cadenas 
		}
    	return lista;
    }
    
}
