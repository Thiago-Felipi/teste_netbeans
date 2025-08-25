/*
*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.

20:46

muda muda muda 
20:48
mudanças feitas
 */
package cfi028_maridodealuguel_9292_9288_clonagem_github;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Alunos
 */
public class CFI028_MaridoDeAluguel_9292_9288 extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("TelaInicial.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}


/*
aaaaaaaaaaaaaaaaaaaaaaaaaaaaa
teste de ediçao pos clonagem de dirtetorio do git hub como teste
teste teste teste 20:46
aaaas rtes tes tre teste
aaaaaaaaaaaaaaaaaaaaaaaaaaaaa


*/
