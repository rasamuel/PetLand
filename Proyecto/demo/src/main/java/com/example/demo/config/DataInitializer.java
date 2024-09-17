package com.example.demo.config;

import com.example.demo.entidades.Owner;
import com.example.demo.entidades.Pet;
import com.example.demo.entidades.Veterinario;
import com.example.demo.repositorio.OwnerRepository;
import com.example.demo.repositorio.PetRepository;
import com.example.demo.repositorio.VeterinarioRepository;
import com.github.javafaker.Faker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Configuration
public class DataInitializer {

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private VeterinarioRepository veterinarioRepository;


    // Lista de enfermedades comunes en perros
    private static final List<String> enfermedadesComunes = Arrays.asList(
        "Parvovirus",         
        "Moquillo",                 
        "Rabia",                    
        "Hepatitis canina",         
        "Leptospirosis",            
        "Coronavirus canino",       
        "Tos de las perreras",      
        "Gusano del corazón",     
        "Enfermedad de Lyme",       
        "Sarna",                  
        "Candidiasis",            
        "Dermatitis atópica",      
        "Cáncer",                 
        "Hip displasia",           
        "Diabetes",                
        "Insuficiencia renal",      
        "Hipotiroidismo",        
        "Epilepsia",             
        "Leishmaniasis",         
        "Endoparásitos",            
        "Ectoparásitos",           
        "Infección por hongos",     
        "Osteoartritis"             
    );

    private static final List<String> razas = List.of(
        "bulldog-frances",         
        "labrador-retriever",     
        "pastor-aleman",      
        "beagle",                
        "poodle",                
        "rottweiler",            
        "boxer",                
        "chihuahua",                
        "dachshund",               
        "schnauzer",              
        "pomerania",                
        "yorkshire-terrier",      
        "doberman",               
        "shih-tzu",               
        "cocker-spaniel",         
        "gran-danés",              
        "husky",                   
        "pitbull",                
        "west-highland-white-terrier", 
        "boston-terrier",        
        "maltés",                   
        "jack-russell-terrier",   
        "basset-hound",           
        "cocker",                
        "pug",                    
        "lhasa-apso",            
        "poodle-miniatura",     
        "poodle-toy",           
        "australian-shepherd",    
        "border-collie",         
        "samoyedo",              
        "alaskan-malamute",       
        "chow-chow",              
        "schnauzer-miniatura",    
        "salchicha",               
        "bulldog",                  
        "doberman-pinscher"       
    );

    private static final List<String> dogImageUrls = Arrays.asList(
        "https://images.dog.ceo/breeds/spaniel-brittany/n02101388_454.jpg",
"https://images.dog.ceo/breeds/mexicanhairless/n02113978_216.jpg",
"https://images.dog.ceo/breeds/hound-basset/n02088238_10072.jpg",
"https://images.dog.ceo/breeds/retriever-golden/n02099601_118.jpg",
"https://images.dog.ceo/breeds/mastiff-bull/n02108422_1701.jpg",
"https://images.dog.ceo/breeds/dalmatian/cooper2.jpg",
"https://images.dog.ceo/breeds/dhole/n02115913_2412.jpg",
"https://images.dog.ceo/breeds/weimaraner/n02092339_4169.jpg",
"https://images.dog.ceo/breeds/dane-great/n02109047_20770.jpg",
"https://images.dog.ceo/breeds/waterdog-spanish/20180706_194432.jpg",
"https://images.dog.ceo/breeds/beagle/1374053345_Milo.jpg",
"https://images.dog.ceo/breeds/setter-gordon/n02101006_3103.jpg",
"https://images.dog.ceo/breeds/briard/n02105251_3551.jpg",
"https://images.dog.ceo/breeds/gaddi-indian/Gaddi.jpg",
"https://images.dog.ceo/breeds/hound-basset/n02088238_6012.jpg",
"https://images.dog.ceo/breeds/pointer-germanlonghair/hans1.jpg",
"https://images.dog.ceo/breeds/sharpei/noel.jpg",
"https://images.dog.ceo/breeds/wolfhound-irish/n02090721_4073.jpg",
"https://images.dog.ceo/breeds/kombai/Kombai-indian-Dog.jpg",
"https://images.dog.ceo/breeds/terrier-toy/n02087046_296.jpg",
"https://images.dog.ceo/breeds/terrier-westhighland/n02098286_6158.jpg",
"https://images.dog.ceo/breeds/mastiff-bull/n02108422_2990.jpg",
"https://images.dog.ceo/breeds/terrier-cairn/n02096177_1255.jpg",
"https://images.dog.ceo/breeds/cavapoo/doggo1.jpg",
"https://images.dog.ceo/breeds/clumber/n02101556_2566.jpg",
"https://images.dog.ceo/breeds/otterhound/n02091635_3900.jpg",
"https://images.dog.ceo/breeds/mountain-bernese/n02107683_5538.jpg",
"https://images.dog.ceo/breeds/hound-blood/n02088466_7229.jpg",
"https://images.dog.ceo/breeds/segugio-italian/n02090722_001.jpg",
"https://images.dog.ceo/breeds/terrier-kerryblue/n02093859_1697.jpg",
"https://images.dog.ceo/breeds/beagle/n02088364_14055.jpg",
"https://images.dog.ceo/breeds/whippet/n02091134_15210.jpg",
"https://images.dog.ceo/breeds/australian-kelpie/Resized_20200416_142905_108884348190285.jpg",
"https://images.dog.ceo/breeds/pyrenees/n02111500_1314.jpg",
"https://images.dog.ceo/breeds/pointer-german/n02100236_3259.jpg",
"https://images.dog.ceo/breeds/retriever-golden/n02099601_846.jpg",
"https://images.dog.ceo/breeds/bulldog-english/mami.jpg",
"https://images.dog.ceo/breeds/whippet/n02091134_14465.jpg",
"https://images.dog.ceo/breeds/poodle-toy/n02113624_9574.jpg",
"https://images.dog.ceo/breeds/mudhol-indian/Indian-Mudhol.jpg",
"https://images.dog.ceo/breeds/bulldog-french/n02108915_3369.jpg",
"https://images.dog.ceo/breeds/germanshepherd/n02106662_6966.jpg",
"https://images.dog.ceo/breeds/collie-border/pippin3.jpg",
"https://images.dog.ceo/breeds/havanese/00100trPORTRAIT_00100_BURST20191126134713895_COVER.jpg",
"https://images.dog.ceo/breeds/waterdog-spanish/20180714_201544.jpg",
"https://images.dog.ceo/breeds/pyrenees/n02111500_1358.jpg",
"https://images.dog.ceo/breeds/spaniel-japanese/n02085782_697.jpg",
"https://images.dog.ceo/breeds/bakharwal-indian/Bakharwal.jpg",
"https://images.dog.ceo/breeds/mastiff-tibetan/n02108551_1409.jpg",
"https://images.dog.ceo/breeds/deerhound-scottish/n02092002_1029.jpg",
"https://images.dog.ceo/breeds/samoyed/n02111889_691.jpg",
"https://images.dog.ceo/breeds/kombai/Kombai-indian-Dog.jpg",
"https://images.dog.ceo/breeds/pembroke/n02113023_2330.jpg",
"https://images.dog.ceo/breeds/pyrenees/n02111500_5057.jpg",
"https://images.dog.ceo/breeds/whippet/n02091134_3472.jpg",
"https://images.dog.ceo/breeds/hound-basset/n02088238_3066.jpg",
"https://images.dog.ceo/breeds/hound-basset/n02088238_1261.jpg",
"https://images.dog.ceo/breeds/spaniel-irish/n02102973_782.jpg",
"https://images.dog.ceo/breeds/terrier-wheaten/n02098105_1152.jpg",
"https://images.dog.ceo/breeds/labradoodle/lola.jpg",
"https://images.dog.ceo/breeds/spaniel-japanese/n02085782_309.jpg",
"https://images.dog.ceo/breeds/terrier-kerryblue/n02093859_729.jpg",
"https://images.dog.ceo/breeds/vizsla/n02100583_11289.jpg",
"https://images.dog.ceo/breeds/cockapoo/bubbles2.jpg",
"https://images.dog.ceo/breeds/shiba/shiba-6.jpg",
"https://images.dog.ceo/breeds/chow/n02112137_9708.jpg",
"https://images.dog.ceo/breeds/hound-afghan/n02088094_4307.jpg",
"https://images.dog.ceo/breeds/cockapoo/Scout.jpg",
"https://images.dog.ceo/breeds/hound-walker/n02089867_712.jpg",
"https://images.dog.ceo/breeds/australian-kelpie/IMG_7387.jpg",
"https://images.dog.ceo/breeds/bouvier/n02106382_4034.jpg",
"https://images.dog.ceo/breeds/samoyed/n02111889_17827.jpg",
"https://images.dog.ceo/breeds/bouvier/n02106382_3770.jpg",
"https://images.dog.ceo/breeds/retriever-flatcoated/n02099267_5306.jpg",
"https://images.dog.ceo/breeds/hound-plott/hhh-23456.jpg",
"https://images.dog.ceo/breeds/sheepdog-shetland/n02105855_9415.jpg",
"https://images.dog.ceo/breeds/terrier-westhighland/n02098286_2139.jpg",
"https://images.dog.ceo/breeds/spitz-japanese/tofu.jpg",
"https://images.dog.ceo/breeds/corgi-cardigan/n02113186_12513.jpg",
"https://images.dog.ceo/breeds/terrier-welsh/lucy.jpg",
"https://images.dog.ceo/breeds/coonhound/n02089078_1668.jpg",
"https://images.dog.ceo/breeds/mudhol-indian/Indian-Mudhol.jpg",
"https://images.dog.ceo/breeds/pointer-germanlonghair/hans2.jpg",
"https://images.dog.ceo/breeds/chippiparai-indian/Indian-Chippiparai.jpg",
"https://images.dog.ceo/breeds/cotondetulear/100_2013.jpg",
"https://images.dog.ceo/breeds/poodle-miniature/n02113712_8756.jpg",
"https://images.dog.ceo/breeds/terrier-australian/n02096294_3137.jpg",
"https://images.dog.ceo/breeds/spaniel-japanese/n02085782_697.jpg",
"https://images.dog.ceo/breeds/deerhound-scottish/n02092002_5462.jpg",
"https://images.dog.ceo/breeds/setter-irish/n02100735_6084.jpg",
"https://images.dog.ceo/breeds/terrier-australian/n02096294_5877.jpg",
"https://images.dog.ceo/breeds/hound-plott/n02096585_1440.jpg",
"https://images.dog.ceo/breeds/schnauzer-miniature/n02096585_1375.jpg",
"https://images.dog.ceo/breeds/papillon/n02096585_2147.jpg",
"https://images.dog.ceo/breeds/dachshund-long/n02096585_1632.jpg",
"https://images.dog.ceo/breeds/poodle-miniature/n02113712_7160.jpg",
"https://images.dog.ceo/breeds/germanshepherd/n02106662_6516.jpg",
"https://images.dog.ceo/breeds/terrier-cairn/n02096177_3011.jpg",
"https://images.dog.ceo/breeds/germanshepherd/n02106662_2346.jpg",
"https://images.dog.ceo/breeds/germanshepherd/n02106662_5608.jpg",
"https://images.dog.ceo/breeds/retriever-golden/n02099601_2286.jpg",
"https://images.dog.ceo/breeds/cockapoo/DSC_1171.jpg",
"https://images.dog.ceo/breeds/terrier-australian/n02096294_1462.jpg",
"https://images.dog.ceo/breeds/retriever-labrador/n02096585_3281.jpg",
"https://images.dog.ceo/breeds/saintbernard/n02110185_2995.jpg",
"https://images.dog.ceo/breeds/papillon/n02096585_1876.jpg",
"https://images.dog.ceo/breeds/schnauzer-miniature/n02096585_4683.jpg",
"https://images.dog.ceo/breeds/dachshund-long/n02096585_1641.jpg",
"https://images.dog.ceo/breeds/setter-irish/n02100735_1381.jpg"
);


    @Bean
    public CommandLineRunner loadData() {
        return (args) -> {
            Faker faker = new Faker();
            Random random = new Random();

            // Crear 50 propietarios con datos reales generados por Faker
            for (int i = 1; i <= 50; i++) {
                Owner owner = new Owner();
                owner.setNombre(faker.name().fullName());
                owner.setCedula(faker.idNumber().valid());
                owner.setCorreo(faker.internet().emailAddress());
                owner.setCelular(faker.phoneNumber().cellPhone());
                ownerRepository.save(owner);

                // Crear 2 mascotas por propietario
                for (int j = 1; j <= 2; j++) {
                    Pet pet = new Pet();
                    pet.setNombre(faker.dog().name());

                    String breed = razas.get(random.nextInt(razas.size()));
                    pet.setRaza(breed);

                    // Obtener URL de imagen de la raza específica
                    String imageUrl = dogImageUrls.get(random.nextInt(dogImageUrls.size()));
                    pet.setImageUrl(imageUrl);

                    pet.setEdad(faker.number().numberBetween(1, 15));
                    pet.setPeso(faker.number().randomDouble(2, 5, 40)); // Peso entre 5 y 40 kg

                    // Generar estado usando Faker
                    boolean estado = true;
                    pet.setEstado(estado);

                    // Seleccionar aleatoriamente una enfermedad común en perros
                    String enfermedad = enfermedadesComunes.get(random.nextInt(enfermedadesComunes.size()));
                    pet.setEnfermedad(enfermedad);

                    pet.setOwner(owner);
                    petRepository.save(pet);
                }
            }
            List<Veterinario> veterinarios = List.of(
                new Veterinario("Juan Pérez", "12345", "Cirugía", "url/to/foto1.jpg"),
                new Veterinario("Ana Gómez", "12345", "Dermatología", "url/to/foto2.jpg"),
                new Veterinario("Luis Martínez", "12345", "Oftalmología", "url/to/foto3.jpg"),
                new Veterinario("Carla Rodríguez", "12345", "Ortopedia", "url/to/foto4.jpg"),
                new Veterinario("Miguel Sánchez", "12345", "Cardiología", "url/to/foto5.jpg"),
                new Veterinario("Laura Fernández", "12345", "Oncología", "url/to/foto6.jpg"),
                new Veterinario("Pablo Hernández", "12345", "Neurología", "url/to/foto7.jpg"),
                new Veterinario("Silvia López", "12345", "Odontología", "url/to/foto8.jpg"),
                new Veterinario("Eduardo Torres", "12345", "Anestesiología", "url/to/foto9.jpg"),
                new Veterinario("Gabriela Ramírez", "12345", "Medicina General", "url/to/foto10.jpg"),
                new Veterinario("Ricardo Méndez", "12345", "Cirugía", "url/to/foto11.jpg"),
                new Veterinario("Sofía Morales", "12345", "Dermatología", "url/to/foto12.jpg"),
                new Veterinario("Fernando Ortiz", "12345", "Oftalmología", "url/to/foto13.jpg"),
                new Veterinario("Mariana Castro", "12345", "Ortopedia", "url/to/foto14.jpg"),
                new Veterinario("Sergio Aguirre", "12345", "Cardiología", "url/to/foto15.jpg"),
                new Veterinario("Patricia Vega", "12345", "Oncología", "url/to/foto16.jpg"),
                new Veterinario("Álvaro Navarro", "12345", "Neurología", "url/to/foto17.jpg"),
                new Veterinario("Camila Jiménez", "12345", "Odontología", "url/to/foto18.jpg"),
                new Veterinario("Andrés Castro", "12345", "Anestesiología", "url/to/foto19.jpg"),
                new Veterinario("Paula Rivas", "12345", "Medicina General", "url/to/foto20.jpg")
            );
        
            veterinarios.forEach(veterinarioRepository::save);
        };
    }

}
