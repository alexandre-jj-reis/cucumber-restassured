package passos;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import java.util.Map;

import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;
import org.apache.commons.lang3.StringUtils;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class LivroPassos {

	private Response response;
	private ValidatableResponse json;
	private RequestSpecification request;

	private String ENDPOINT = "https://www.googleapis.com/books/v1/volumes";

	@Dado("que existe um livro com um isbn de (.*)")
	public void que_existe_um_livro_com_um_isbn(String isbn){
		request = given().param("q", "isbn:" + isbn);
	}

	@Quando("um usuário recupera o livro por isbn")
	public void um_usuario_recupera_o_livro_por_isbn(){
		response = request.when().get(ENDPOINT);
		System.out.println("response: " + response.prettyPrint());
	}

	@Entao("o código de status é (\\d+)")
	public void codigo_de_status(int statusCode){
		json = response.then().statusCode(statusCode);
	}

	@E("a resposta inclui o seguinte$")
	public void a_resposta_inclui_o_seguinte(Map<String,String> responseFields){
		for (Map.Entry<String, String> field : responseFields.entrySet()) {
			if(StringUtils.isNumeric(field.getValue())){
				json.body(field.getKey(), equalTo(Integer.parseInt(field.getValue())));
			}
			else{
				json.body(field.getKey(), equalTo(field.getValue()));
			}
		}
	}

	@E("a resposta inclui o seguinte em qualquer ordem")
	public void a_resposta_inclui_o_seguinte_em_qualquer_ordem(Map<String,String> responseFields){
		for (Map.Entry<String, String> field : responseFields.entrySet()) {
			if(StringUtils.isNumeric(field.getValue())){
				json.body(field.getKey(), containsInAnyOrder(Integer.parseInt(field.getValue())));
			}
			else{
				json.body(field.getKey(), containsInAnyOrder(field.getValue()));
			}
		}
	}

	@Dado("que existe um livro com o etag (.*)")
	public void que_existe_um_livro_com_o_nome(String etag){
		request = given().param("q", "id:" + etag);
	}

	@Quando("um usuário recupera o livro pelo seu etag")
	public void um_usuario_recupera_o_livro_pelo_seu_titulo(){
		response = request.when().get(ENDPOINT);
		System.out.println("response: " + response.prettyPrint());
	}

	@E("a resposta inclui os seguintes titulos$")
	public void a_Resposta_Inclui_Os_Seguintes_Titulos(Map<String,String> responseFields) {
		for (Map.Entry<String, String> field : responseFields.entrySet()) {
			if(StringUtils.isNumeric(field.getValue())){
				json.body(field.getKey(), equalTo(Integer.parseInt(field.getValue())));
			}
			else{
				json.body(field.getKey(), stringContainsInOrder(field.getValue()));
			}
		}
	}
}


