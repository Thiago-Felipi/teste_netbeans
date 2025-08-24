package cfi028_maridodealuguel_9292_9288;

import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import java.util.List;
import java.util.Optional;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Utilitarios {

  // Método que exibe mensagens de alerta
  public static boolean msgInformacao(String msg, int tipo) {
    Alert.AlertType tipoAlerta;

    // Determina o tipo de alerta
    switch (tipo) {
      case 1:
        tipoAlerta = Alert.AlertType.ERROR;
        break;
      case 2:
        tipoAlerta = Alert.AlertType.CONFIRMATION;
        break;
      default:
        tipoAlerta = Alert.AlertType.INFORMATION;
    }

    Alert alerta = new Alert(tipoAlerta);
    alerta.setTitle("Mensagem");
    alerta.setHeaderText(msg);
    alerta.setContentText(msg);
    Optional<ButtonType> result = alerta.showAndWait();

    // Se for confirmação, retorna true ou false
    if (tipoAlerta == Alert.AlertType.CONFIRMATION) {
    //retorna verdadeiro ase clicou em button ok 
      if (result.get() == ButtonType.OK) {
        return true;
      }
    }
   // Para INFORMATION ou ERROR, só exibe a mensagem e retorna false por padrão
    return false;
  }

  // Método para ler o texto do RadioButton selecionado em um ToggleGroup
  public static String lerRadioButtonSelecionado(ToggleGroup tgCores) {
    String textoSelecionado = null;
    RadioButton selecionado = (RadioButton) tgCores.getSelectedToggle();
    if (selecionado != null) {
      textoSelecionado = selecionado.getText();
    }
    return textoSelecionado;
  }

  // Método para ler os CheckBoxes selecionados e retornar o texto
  public static String lerCheckBoxesSelecionados(List<CheckBox> checkBoxes) {
    String opcionais = "";
    for (CheckBox checkBox : checkBoxes) {
      if (checkBox.isSelected()) {
        opcionais += checkBox.getText() + ", ";
      }
    }
    // Remove a última vírgula e espaço, se existirem
    if (opcionais.endsWith(", ")) {
      opcionais = opcionais.substring(0, opcionais.length() - 2);
    }
    return opcionais;
  }

  public static void soTexto(KeyEvent event) {
    String character = event.getCharacter();
    if (!character.matches("[a-zA-Zá-úÁ-ÚçÇ ]") && event.getCode() != KeyCode.BACK_SPACE) {
      event.consume();  // Bloqueia a entrada do caractere inválido
    }
  }

  public static void validarPrecoDigitado(KeyEvent event, TextField txtPreco) {
    String c = event.getCharacter();  // Obtém o caractere digitado como string
    String texto = txtPreco.getText();

    // Permite apenas números, vírgula e backspace
    if (!c.matches("[0-9,]") && event.getCode() != KeyCode.BACK_SPACE) {
      event.consume();  // Cancela a entrada inválida
      return;
    }

    // Garante que apenas uma vírgula seja digitada
    if (c.equals(",") && texto.contains(",")) {
      event.consume();
      return;
    }

    // Limita o número de casas decimais a duas
    if (texto.contains(",")) {
      int index = texto.indexOf(",");
      if (texto.length() - index > 2 && !event.getCode().equals(KeyCode.BACK_SPACE)) {
        event.consume();
      }
    }
  }
  // Método para validar CPF

  public static boolean isCpfValido(String cpf) {
    cpf = cpf.replaceAll("\\D", ""); // Remove todos os caracteres não numéricos

    if (cpf.length() != 11 || cpf.matches("(\\d)\\1{10}")) {
      return false; // Verifica o tamanho e padrões repetidos
    }
    int soma = 0, peso = 10;
    for (int i = 0; i < 9; i++) {
      soma += (cpf.charAt(i) - '0') * peso--;
    }
    int digito1 = 11 - (soma % 11);
    if (digito1 >= 10) {
      digito1 = 0;
    }

    soma = 0;
    peso = 11;
    for (int i = 0; i < 10; i++) {
      soma += (cpf.charAt(i) - '0') * peso--;
    }
    int digito2 = 11 - (soma % 11);
    if (digito2 >= 10) {
      digito2 = 0;
    }

    return (cpf.charAt(9) - '0' == digito1) && (cpf.charAt(10) - '0' == digito2);
  }

  // Método para validar CNPJ
  public static boolean isCnpjValido(String cnpj) {
    cnpj = cnpj.replaceAll("\\D", ""); // Remove todos os caracteres não numéricos

    if (cnpj.length() != 14) {
      return false;
    }

    int[] peso1 = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
    int[] peso2 = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

    int soma = 0;
    for (int i = 0; i < 12; i++) {
      soma += (cnpj.charAt(i) - '0') * peso1[i];
    }
    int digito1 = soma % 11 < 2 ? 0 : 11 - (soma % 11);

    soma = 0;
    for (int i = 0; i < 13; i++) {
      soma += (cnpj.charAt(i) - '0') * peso2[i];
    }
    int digito2 = soma % 11 < 2 ? 0 : 11 - (soma % 11);

    return (cnpj.charAt(12) - '0' == digito1) && (cnpj.charAt(13) - '0' == digito2);
  }
}
