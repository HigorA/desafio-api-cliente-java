package br.com.desafio.controllers.OpenApi;

import br.com.desafio.model.PagedResource;
import br.com.desafio.model.vo.ClienteVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "Cliente", description = "Endpoint to manage clientes.")
public interface ControllerOpenApi {

    @Operation(summary = "Encontra todos os clientes.", description = "Encontra todos os clientes vindos de uma Api Externa.",
            tags = {"Cliente"},
            responses = {
                    @ApiResponse(description = "Sucess!", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = PagedResource.class))
                    ),
                    @ApiResponse(description = "No Content.", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request.", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized.", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found.", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error.", responseCode = "500", content = @Content),
            })
    public ResponseEntity<PagedResource> findAllFromApi(
             Integer pageNumber,
             Integer pageSize,
             Integer menorIdade,
             Integer maiorIdade,
             String sexo,
             String aniversario
    );

    @Operation(summary = "Encontra todos os clientes e adiciona o campo ID.", description = "Encontra todos os clientes vindos de uma" +
            "Api Externa e adiciona o campo ID.",
            tags = {"Cliente"},
            responses = {
                    @ApiResponse(description = "Sucess!", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = PagedResource.class))
                    ),
                    @ApiResponse(description = "No Content.", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request.", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized.", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found.", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error.", responseCode = "500", content = @Content),
            })
    public ResponseEntity<PagedResource> findAllFromApiWithId(
             Integer pageNumber,
             Integer pageSize,
             Integer menorIdade,
             Integer maiorIdade,
             String sexo,
             String aniversario
    );

    @Operation(summary = "Encontra todos os clientes e adiciona o campo ID.", description = "Encontra todos os clientes vindos de um" +
            "arquivo CSV.",
            tags = {"Cliente"},
            responses = {
                    @ApiResponse(description = "Sucess!", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = PagedResource.class))
                    ),
                    @ApiResponse(description = "No Content.", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request.", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized.", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found.", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error.", responseCode = "500", content = @Content),
            })
    public ResponseEntity<PagedResource> findAllFromCsv(
            Integer pageNumber,
            Integer pageSize,
            Integer menorIdade,
            Integer maiorIdade,
            String sexo,
            String aniversario
    );

    @Operation(summary = "Cadastra um cliente.", description = "Cadastra um cliente no arquivo CSV",
            tags = {"Cliente"},
            responses = {
                    @ApiResponse(description = "Sucess!", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = ClienteVO.class))
                    ),
                    @ApiResponse(description = "No Content.", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request.", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found.", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error.", responseCode = "500", content = @Content),
            })
    public ResponseEntity<?> save(ClienteVO clienteVO);

    @Operation(summary = "Atualiza um cliente.", description = "Atualiza um cliente no arquivo CSV",
            tags = {"Cliente"},
            responses = {
                    @ApiResponse(description = "Sucess!", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = ClienteVO.class))
                    ),
                    @ApiResponse(description = "No Content.", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request.", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Internal Error.", responseCode = "500", content = @Content),
            })
    public ResponseEntity<?> update(ClienteVO clienteVO);

    @Operation(summary = "Exclui um cliente.", description = "Exclui um cliente no arquivo CSV",
            tags = {"Cliente"},
            responses = {
                    @ApiResponse(description = "Sucess!", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = ClienteVO.class))
                    ),
                    @ApiResponse(description = "Bad Request.", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Internal Error.", responseCode = "500", content = @Content),
            })
    public ResponseEntity<Void> delete(Integer id);
}
