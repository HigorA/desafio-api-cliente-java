package br.com.desafio.unittests.service;

import br.com.desafio.model.Cliente;
import br.com.desafio.model.vo.ClienteVO;
import br.com.desafio.search.ClienteFiltro;
import br.com.desafio.search.ClienteParametroFiltro;
import br.com.desafio.search.ClienteVOFiltro;
import br.com.desafio.unittests.mocks.MockCliente;
import br.com.desafio.util.ClienteUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class ClienteTest {

    MockCliente input;

    @BeforeEach
    void setUpMocks() throws Exception {
        input = new MockCliente();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void converteAniversarioParaData() {
        Cliente cliente = input.mockEntity(1);

        assertEquals("cliente teste1", cliente.getNome());
        cliente.setAniversario(
                ClienteUtil.convertAniversario(cliente.getAniversario(),
                        cliente.getIdade()));
        assertEquals("01/01/22", cliente.getAniversario());
    }

    @Test
    void geraId() {
        List<ClienteVO> vos = input.mockListVO();
        vos = ClienteUtil.incrementId(vos);

        assertEquals(1, vos.get(0).getId());
        assertEquals(4, vos.get(3).getId());
        assertEquals(7, vos.get(6).getId());
        assertEquals(10, vos.get(9).getId());
    }

    @Test
    void filtroClientePorSexo() {
        List<Cliente> clientes = input.mockListEntity();
        ClienteParametroFiltro filtro = new ClienteParametroFiltro();
        filtro.setSexo("m");

        List<Cliente> clientesFiltrados = ClienteFiltro.filtro(filtro, clientes);
        clientesFiltrados.forEach(cliente -> assertEquals("M", cliente.getSexo()));
    }

    @Test
    void filtroClienteVOPorSexo() {
        List<ClienteVO> clientes = input.mockListVO();
        ClienteParametroFiltro filtro = new ClienteParametroFiltro();
        filtro.setSexo("m");

        List<ClienteVO> clientesFiltrados = ClienteVOFiltro.filtro(filtro, clientes);
        clientesFiltrados.forEach(cliente -> assertEquals("M", cliente.getSexo()));
    }
}
