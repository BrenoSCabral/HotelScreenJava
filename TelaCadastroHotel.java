package telacadastrohotel;

import java.util.HashMap;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import static javafx.scene.paint.Color.RED;
import javafx.stage.Stage;

/**
 *
 * @author breno
 */
public class TelaCadastroHotel extends Application {
    
    //O exercicio nao deixou claro se era necessario implementar ao modelo de hotel ou somente fazer
    // a tela. Como foi ensinado somente a fazer a tela, eh isso que estarei fazendo.
    @Override
    public void start(Stage stage) throws Exception {
        Insets padrao = new Insets(0,0,10,0);
        VBox layoutRaiz = new VBox(10);
        layoutRaiz.setPadding(new Insets(10, 10, 10, 10));
        
        BorderPane layoutNome = new BorderPane();
        Label labelNome = new Label ("Nome:");
        labelNome.setPadding(padrao);
        TextField textNome = new TextField();
        layoutNome.setTop(labelNome);
        layoutNome.setBottom(textNome);
        
        BorderPane layoutEndereco = new BorderPane();
        Label labelEndereco = new Label("Endereço:");
        labelEndereco.setPadding(padrao);
        TextField textEndereco = new TextField();
        layoutEndereco.setTop(labelEndereco);
        layoutEndereco.setBottom(textEndereco);
        
        BorderPane layoutRG = new BorderPane();
        Label labelRG = new Label("RG:");
        labelRG.setPadding(padrao);
        TextField textRG = new TextField();
        layoutRG.setTop(labelRG);
        layoutRG.setBottom(textRG);
        
        BorderPane layoutCPF = new BorderPane();
        Label labelCPF = new Label("CPF:");
        labelCPF.setPadding(padrao);
        TextField textCPF = new TextField();
        layoutCPF.setTop(labelCPF);
        layoutCPF.setBottom(textCPF);
        
        BorderPane layoutIdade = new BorderPane();
        Label labelIdade = new Label("Idade:");
        labelIdade.setPadding(padrao);
        TextField textIdade = new TextField();
        layoutIdade.setTop(labelIdade);
        layoutIdade.setBottom(textIdade);
        
        /* O sexo nao foi pedido no modelo, portanto sera excluido
        BorderPane layoutSexo = new BorderPane();
        Label labelSexo = new Label("Sexo:");
        labelSexo.setPadding(padrao);
        RadioButton masculino = new RadioButton("M");
        RadioButton feminino = new RadioButton("F");
        layoutSexo.setTop(labelSexo);
        HBox opcoes = new HBox(masculino, feminino);
        layoutSexo.setBottom(opcoes);
        */
        
        BorderPane layoutQuarto = new BorderPane();
        Label labelQuarto = new Label ("Quarto:");
        labelQuarto.setPadding(padrao);
        ObservableList <String> quartosDisponiveis = 
                FXCollections.observableArrayList(
                        "1","2","3","4","5");       
        ComboBox quarto = new ComboBox(quartosDisponiveis);
        layoutQuarto.setTop(labelQuarto);
        layoutQuarto.setBottom(quarto);
        
        HBox layoutBotoes = new HBox(20);
        Button btnSalvar = new Button("Salvar");
        Button btnCancelar = new Button("Cancelar");
        Button btnBuscar = new Button("Buscar");
        layoutBotoes.setAlignment(Pos. CENTER);
        layoutBotoes.setPadding(new Insets(30, 0, 0, 0));
        layoutBotoes.getChildren().addAll(btnSalvar, btnBuscar, btnCancelar);
        
        // Criacao do Label pra informar o resultado da acao
        BorderPane layoutResultado = new BorderPane();
        Label labelResultado = new Label();
        labelResultado.setTextFill(RED);
        labelResultado.setMaxWidth(Double.MAX_VALUE);
        AnchorPane.setLeftAnchor(labelResultado, 0.0);
        AnchorPane.setRightAnchor(labelResultado, 0.0);
        labelResultado.setAlignment(Pos. CENTER);
        layoutResultado.setTop(labelResultado);
        Label labelProcura = new Label();
        labelProcura.setTextFill(RED);
        labelProcura.setMaxWidth(Double.MAX_VALUE);
        AnchorPane.setLeftAnchor(labelProcura, 0.0);
        AnchorPane.setRightAnchor(labelProcura, 0.0);
        labelProcura.setAlignment(Pos. CENTER);
        layoutResultado.setBottom(labelProcura);
        
        //criando o DB
        Registrador registro = new Registrador();
        // Personalizacao das acoes dos botoes
        btnSalvar.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                boolean verificador =verifica(textNome.getText(), textEndereco.getText(), textRG.getText(), textCPF.getText(), textIdade.getText(), (String) quarto.getValue(), registro);
                if (verificador){
                    Hospede hospede = criaHospede(textNome.getText(), textEndereco.getText(), textRG.getText(), textCPF.getText(), textIdade.getText());
                    Quarto numQuarto= criaQuarto((String) quarto.getValue(), hospede);
                    registro.addRegistro(hospede, numQuarto);
                    // deixando todas as labels em branco novamente
                    labelResultado.setText("");
                    labelProcura.setText("");
                    textNome.setText("");
                    textIdade.setText("");
                    textRG.setText("");
                    textCPF.setText("");
                    textEndereco.setText("");
                }
                String text = verificador ? "Hóspede Cadastrado!" : "Falha para cadastrar hóspede.\nPor favor, cheque novamente os dados inseridos.";
                labelResultado.setText(text);
            }

            private Hospede criaHospede(String nome, String endereco, String RG, String CPF, String idade) {
                Long RGf = Long.parseLong(RG);
                Long CPFf = Long.parseLong(CPF);
                int idadef = Integer.parseInt(idade);
                Hospede hospede = new Hospede(CPFf, RGf, nome, idadef, endereco);

                return hospede; 
            }

            private Quarto criaQuarto(String quartoStr, Hospede hospede){
                int numQuarto = Integer.parseInt(quartoStr);
                Quarto quarto = new Quarto(numQuarto, hospede);
                return quarto;
            }
                
        });
        
        btnBuscar.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                labelResultado.setText("");
                labelProcura.setText("");
                String mensagemProcura = procuraHospede(textNome.getText(), registro);
                labelProcura.setText(mensagemProcura);
            }
        });
        
        
        btnCancelar.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                labelResultado.setText("");
                labelProcura.setText("");
                textNome.setText("");
                textIdade.setText("");
                textRG.setText("");
                textCPF.setText("");
                textEndereco.setText("");
            }
            
        });
        
        
        layoutRaiz.getChildren().addAll(layoutNome, layoutEndereco, layoutRG, layoutCPF, layoutIdade, layoutQuarto, layoutResultado, layoutBotoes);
        Scene scene = new Scene(layoutRaiz, 350, 550);
        stage.setTitle("Cadastro Hotel");
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch();
    }
    
    
    
    // fazendo as verificacoes aqui 
    public static boolean verifica(String nome, String endereco, String RG, String CPF, String idade, String quarto, Registrador registro){
        // atribui uma condicao a cada um dos valores, se for algum falso renderiza novamente o formulario
        int quartoF = Integer.parseInt(quarto);
        boolean c1 = CheckName(nome);
        boolean c2 = CheckLong(RG);
        boolean c3 = CheckLong(CPF);
        boolean c4 = CheckInteger(idade);
        if (c1 && c2 && c3 && c4){
            return true;
        } else {
            return false;
        }
    }
    
    
    /*
    public static Hospede criaHospede(String nome, String endereco, String RG, String CPF, String idade, String quartoStr, Registrador registro){
        int numQuarto = Integer.parseInt(quartoStr);
        Long.parseLong(RG);
        Long.parseLong(CPF);
        Integer.parseInt(idade);
        Hospede hospede = new Hospede(CPF, RG, nome, idade, endereco);
        
        return hospede;    
    }
    
    public static Quarto criaQuarto(String quartoStr, Hospede hospede){
        int numQuarto = Integer.parseInt(quartoStr);
        Quarto quarto = new Quarto(numQuarto, hospede);
        return quarto;
    }

    */
    
    public static String procuraHospede(String nome, Registrador registro){
        return registro.registroToS(nome);
    }
    
    
    public static boolean CheckName(String s){
        if (s == null)  {
         return false;
      }
      int len = s.length();
      for (int i = 0; i < len; i++) {
         if ((Character.isDigit(s.charAt(i)))) {
            return false;
         }
      
        }
        return true;
    }
    
    public static boolean CheckInteger(String numeroStr){
        while (true) {
            try{
            int numero = Integer.parseInt(numeroStr);
                return true;
            } catch(NumberFormatException exception) {
                return false;
            }
        }
    }
    
    public static boolean CheckLong(String numeroStr){
        while (true) {
            try{
                long numero = Long.parseLong(numeroStr);
                return true;
            } catch(NumberFormatException exception) {
                return false;
            }
        }
    }  
}
