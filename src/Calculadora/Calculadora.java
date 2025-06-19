package tarea09;

//Librerías para poder utilizar JavaFX

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;


//Librerías específicas para evaluar expresiones exp4j
/**
 * La típica calculadora básica para realizar cálculos artitméticos como la
 * suma, resta, multiplicación y división, en la que se permite el cálculo de
 * expresiones combinadas.
 *
 * @author profesorado
 */
public class Calculadora extends Application {
    //----------------------------------------------
    //          Declaración de variables 
    //----------------------------------------------
    // Constantes

    // Variables de entrada
    // Variables de salida
    double resultado;   //resultado de la fórmula
    String formula = "";    //Fórmula creada por el usuario
    public TextField pantalla;  //texto donde se presenta la fórmula

    //----------------------------------------------
    //          Declaración de variables auxiliares 
    //----------------------------------------------  
    ExpressionBuilder expressionBuilder;    //Crea un objeto expresionbuilder para la formula
    Expression expression;  //crea una clase expresion para evaluarla.
    String ultimoCaracter = ""; //Último carácter para su posible borrado 
    int parentesisSinCerrar;    //En caso de quedarse un paréntesis sin cerrar

    /*El método start es el punto de entrada para una aplicación JavaFX.
    Su función principal es inicializar y mostrar la interfaz 
    gráfica de usuario (GUI) de la aplicación. Se crea un diseño (layout), 
    se añaden controles (botones, etiquetas, campos, ...) y se crea la escena
    con un estilo, y se muestra el escenario.*/
    @Override
    public void start(Stage escenario) {
        // Crear etiqueta para escribir lo que se pide al usuario

        VBox root = new VBox(); //Creación vertical de los componentes
        pantalla = new TextField(); //creación del texto para presentar la fórmula
        pantalla.setEditable(false);    //Para que no se pueda editar
        //pantalla.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);

        //pantalla.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);   //Orientación de escritura de derecha a izquierda
        //((AbstractDocument) pantalla.getDocument()).setDocumentFilter(new RightToLeftFilter());
        //Creación de los botones
        
        Button boton7 = new Button("7");      
        Button boton8 = new Button("8");
        Button boton9 = new Button("9");
        Button botonDivision = new Button("/");
        Button botonParentesisAp = new Button("(");
        Button boton4 = new Button("4");
        Button boton5 = new Button("5");
        Button boton6 = new Button("6");
        Button botonMultiplicar = new Button("*");
        Button botonParentesisCier = new Button(")");
        Button boton1 = new Button("1");
        Button boton2 = new Button("2");
        Button boton3 = new Button("3");
        Button botonRestar = new Button("-");
        Button botonDecimal = new Button(".");
        Button boton0 = new Button("0");
        Button botonC = new Button("C");
        Button botonBorrar = new Button("<");
        Button botonSumar = new Button("+");
        Button botonIgual = new Button("=");

       
        GridPane botones = new GridPane();
        botones.setHgap(3);
        botones.setVgap(3);

        botones.add(boton7, 0, 0);
        botones.add(boton8, 1, 0);
        botones.add(boton9, 2, 0);
        botones.add(botonDivision, 3, 0);
        botones.add(botonParentesisAp, 4, 0);
        botones.add(boton4, 0, 1);
        botones.add(boton5, 1, 1);
        botones.add(boton6, 2, 1);
        botones.add(botonMultiplicar, 3, 1);
        botones.add(botonParentesisCier, 4, 1);
        botones.add(boton1, 0, 2);
        botones.add(boton2, 1, 2);
        botones.add(boton3, 2, 2);
        botones.add(botonRestar, 3, 2);
        botones.add(botonDecimal, 4, 2);
        botones.add(boton0, 0, 3);
        botones.add(botonC, 1, 3);
        botones.add(botonBorrar, 2, 3);
        botones.add(botonSumar, 3, 3);
        botones.add(botonIgual, 4, 3);
        botones.setPadding(new Insets(5));

        root.getChildren().addAll(pantalla, botones);

       
        //----------------------------------------------
        //                  EVENTOS 
        //----------------------------------------------  
        boton7.setOnAction((ActionEvent e) -> {
            procesoDeEntrada("7");

        });

        boton8.setOnAction((ActionEvent e) -> {
            procesoDeEntrada("8");

        });

        boton9.setOnAction((ActionEvent e) -> {
            procesoDeEntrada("9");
        });

        boton4.setOnAction((ActionEvent e) -> {
            procesoDeEntrada("4");
        });

        boton5.setOnAction((ActionEvent e) -> {
            procesoDeEntrada("5");
        });

        boton6.setOnAction((ActionEvent e) -> {
            procesoDeEntrada("6");
        });

        boton1.setOnAction((ActionEvent e) -> {
            procesoDeEntrada("1");
        });

        boton2.setOnAction((ActionEvent e) -> {
            procesoDeEntrada("2");
        });

        boton3.setOnAction((ActionEvent e) -> {
            procesoDeEntrada("3");
        });

        botonDivision.setOnAction((ActionEvent e) -> {
            procesoDeEntrada("/");
        });

        botonParentesisAp.setOnAction((ActionEvent e) -> {
            procesoDeEntrada("(");
        });

        botonMultiplicar.setOnAction((ActionEvent e) -> {
            procesoDeEntrada("*");
        });

        botonParentesisCier.setOnAction((ActionEvent e) -> {
            procesoDeEntrada(")");
        });

        botonRestar.setOnAction((ActionEvent e) -> {
            procesoDeEntrada("-");
        });

        botonDecimal.setOnAction((ActionEvent e) -> {
            procesoDeEntrada(".");
        });

        boton0.setOnAction((ActionEvent e) -> {
            procesoDeEntrada("0");
        });

        botonC.setOnAction((ActionEvent e) -> {
            pantalla.setText("");   //Borra la pantalla
            formula = "";   // Borra la fórmula
            ultimoCaracter = "";    //también el último carácter
            parentesisSinCerrar = 0;    //Los paréntesis se anulan
        });

        botonBorrar.setOnAction((ActionEvent e) -> {
            procesoDeEntrada("<");
        });

        botonSumar.setOnAction((ActionEvent e) -> {
            procesoDeEntrada("+");
        });

        botonIgual.setOnAction((ActionEvent e) -> {
            procesoDeEntrada("=");
        });

      
        root.getStyleClass().add("root");
        Scene scene = new Scene(root, 425, 400);
        scene.getStylesheets().add(getClass().getResource("calculadora.css").toExternalForm());
        //scene.getStylesheets().add("calculadora.css");
        escenario.setTitle("Mi Calculadora");
        escenario.setResizable(false);  //No se podrá modificar el tamaño de la ventana
        escenario.getIcons().add(new Image(getClass().getResourceAsStream("logoCalcu.png")));
        escenario.setScene(scene);
        escenario.show();
        
        
        
         //----------------------------------------------
        //                  Cambios en diseño 
        //----------------------------------------------  
        // Inserción en cada componente de los modificadores de estilos del CSS
        // Incluye todos los componentes        
        //Del textField del CSS
        pantalla.getStyleClass().add("text-field");
        
        //Del botón igual
        botonIgual.getStyleClass().add("igual");        
        botonIgual.getStyleClass().add("igual:hover");
        botonIgual.getStyleClass().add("igual:pressed");

        //Del botón limpiar o "botonC" 
      
        botonC.getStyleClass().add("limpiar");
        botonC.getStyleClass().add("limpiar:hover");
        botonC.getStyleClass().add("limpiar:pressed");
        
        //botón borrar último caracter
     
        botonBorrar.getStyleClass().add("limpiar");
        botonBorrar.getStyleClass().add("limpiar:hover");
        botonBorrar.getStyleClass().add("limpiar:pressed");

        //De todos los botones operadores
        
        botonDivision.getStyleClass().add("operador");
        botonDivision.getStyleClass().add("operador:hover");
        botonDivision.getStyleClass().add("operador:pressed");

      
        botonMultiplicar.getStyleClass().add("operador");
        botonMultiplicar.getStyleClass().add("operador:hover");
        botonMultiplicar.getStyleClass().add("operador:pressed");

        
        botonSumar.getStyleClass().add("operador");
        botonSumar.getStyleClass().add("operador:hover");
        botonSumar.getStyleClass().add("operador:pressed");
      
        botonRestar.getStyleClass().add("operador");
        botonRestar.getStyleClass().add("operador:hover");
        botonRestar.getStyleClass().add("operador:pressed");

        //De los botones paréntesis y decimal        
       
        botonParentesisAp.getStyleClass().add("operador");
        botonParentesisAp.getStyleClass().add("operador:hover");
        botonParentesisAp.getStyleClass().add("operador:pressed");

        
        botonParentesisCier.getStyleClass().add("operador");
        botonParentesisCier.getStyleClass().add("operador:hover");
        botonParentesisCier.getStyleClass().add("operador:pressed");

     
        botonDecimal.getStyleClass().add("operador");
        botonDecimal.getStyleClass().add("operador:hover");
        botonDecimal.getStyleClass().add("operador:pressed");

        //De los botones de números  
        
        boton0.getStyleClass().add(":hover");
        boton0.getStyleClass().add(":pressed");

       
        boton1.getStyleClass().add(":hover");
        boton1.getStyleClass().add(":pressed");

       
        boton2.getStyleClass().add(":hover");
        boton2.getStyleClass().add(":pressed");

      
        boton3.getStyleClass().add(":hover");
        boton3.getStyleClass().add(":pressed");

        
        boton4.getStyleClass().add(":hover");
        boton4.getStyleClass().add(":pressed");

       
        boton5.getStyleClass().add(":hover");
        boton5.getStyleClass().add(":pressed");

        
        boton6.getStyleClass().add(":hover");
        boton6.getStyleClass().add(":pressed");

    
        boton7.getStyleClass().add(":hover");
        boton7.getStyleClass().add(":pressed");

        
        boton8.getStyleClass().add(":hover");
        boton8.getStyleClass().add(":pressed");

        
        boton9.getStyleClass().add(":hover");
        boton9.getStyleClass().add(":pressed");
        
        
        

    }

    /**
     * El método procesoDeEntrada maneja la entrada de datos en una calculadora.
     * Toma una cadena de texto (entrada) y realiza diferentes acciones según el
     * contenido de esa cadena, agregando al campo de texto, estableciendo el
     * estado, controlando la adición de puntos decimales para evitar múltiples
     * decimales en un número o evaluando la expresión mátemática para mostrar
     * el resultado haciendo uso de la librería Exp4J.
     *
     * @param entrada Texto recogido de los diferentes textoBotones de la
     * calculadora.
     */
    public void procesoDeEntrada(String entrada) {

        switch (entrada) {
            case "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" -> {
                formula += entrada;
                ultimoCaracter = entrada;   //Se convierte en el último carácter
                pantalla.setText(String.format("%s", formula)); //Presenta el carácter en pantalla     
                //Este tipo de case libra de usar la instrucción break;
            }
            case "<" -> {
                if (formula.length() == 2) {    //Para dos caracteres de la fórmula
                    //reconvierte la fórmula con un carácter menos
                    formula = formula.substring(0, formula.length() - 1);
                    ultimoCaracter = "";    //Por ser ya el penúltimo no habría último
                } else if (formula.length() > 2) { //Para los demás casos
                    formula = formula.substring(0, formula.length() - 1);
                    ultimoCaracter = formula.substring(formula.length() - 2, formula.length() - 1);
                } else {
                    formula = ""; // Si tiene un solo carácter, lo eliminamos completamente
                    ultimoCaracter = "";
                    parentesisSinCerrar = 0;
                }
                pantalla.setText(formula);               
            }          
            case "." -> {
                ultimoCaracter = entrada;
                pantalla.setText(String.format("%s%s",formula, entrada));
                formula += entrada;                     
            }            
            case "+", "-", "*", "/" -> {
                if (ultimoCaracter.equals(")") || ultimoCaracter.matches("[0-9]")) {                    
                    ultimoCaracter = entrada;
                    pantalla.setText(String.format("%s%s",formula, entrada));                    
                    formula += entrada;
                }                
            }            
            case "(" -> {
                if (ultimoCaracter.matches("[0-9]")) {                   
                    formula += "*"; // Se añade el asterisco antes que el paréntesis
                    formula += entrada;
                    ultimoCaracter = entrada;
                    pantalla.setText(String.format("%s",formula));
                    parentesisSinCerrar++;
                } else {                    
                    ultimoCaracter = entrada;
                    parentesisSinCerrar++;  //Cuenta el paréntesis abierto, uno más
                    pantalla.setText(String.format("%s%s", formula, "("));
                    formula += entrada;
                }                      
            }
           
            case ")" -> {
                //Mientras haya un paréntesis sin cerrar y...
                if (parentesisSinCerrar > 0 && ultimoCaracter.equals(")")
                        || ultimoCaracter.matches("[0-9]")) {
                    formula += entrada;
                    ultimoCaracter = entrada;
                    parentesisSinCerrar--;  //resta número de paréntesis sin cerrar
                    pantalla.setText(formula);
                }                
            }           
            case "=" -> {
                try {
                    expressionBuilder = new ExpressionBuilder(formula); //instancia el objeto de la fórmula
                    expression = expressionBuilder.build(); //Construye o transforma a objeto expression
                    resultado = expression.evaluate();  //Evalúa o calcula la expresión
                    formula += "=";
                    formula += String.valueOf(resultado);
                    this.pantalla.setText(String.valueOf(formula)); // para presentar en pantalla la fórmula
                } catch (IllegalArgumentException | ArithmeticException e) {
                    this.pantalla.setText("Error en la expresión.");
                    parentesisSinCerrar = 0;    //Inicializa a cero los paréntesis para empezar a contarlos
                }                
            }            
        }
    }

    /**
     * Programa principal, lanza la aplicación.
     *
     * @param args Argumentos o parámetros (no hay en este caso)
     */
    public static void main(String[] args) {
        launch(args);
    }
}
