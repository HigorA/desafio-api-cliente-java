package br.com.desafio.services;

import br.com.desafio.dao.ClienteDAO;
import br.com.desafio.dao.csv.CsvOperations;
import br.com.desafio.exceptions.RequiredObjectIsNullException;
import br.com.desafio.exceptions.ResourceNotFoundException;
import br.com.desafio.model.Cliente;
import br.com.desafio.model.PagedResource;
import br.com.desafio.model.vo.ClienteVO;
import br.com.desafio.search.ClienteFiltro;
import br.com.desafio.search.ClienteParametroFiltro;
import br.com.desafio.search.ClienteVOFiltro;
import br.com.desafio.util.ClienteUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    private final ClienteDAO dao = new ClienteDAO();

    public PagedResource findAll(ClienteParametroFiltro filtro, Integer pageNumber, Integer pageSize) {
        List<Cliente> clientes = dao.findAll();
        clientes = clientes.stream().sorted(Cliente::compareTo).collect(Collectors.toList());
        clientes.forEach(c -> c.setAniversario(ClienteUtil.convertAniversario(c.getAniversario(), c.getIdade())));
        List<Cliente> filtrado = ClienteFiltro.filtro(filtro, clientes);
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        int start =(int) pageable.getOffset();
        int end = (int) (Math.min((start + pageable.getPageSize()), filtrado.size()));
        Page<Cliente> page = new PageImpl<Cliente>(filtrado.subList(start, end), pageable, filtrado.size());
        return new PagedResource(page);
    }

    public PagedResource findAllWithId(
            Integer menorIdade,
            Integer maiorIdade,
            String sexo,
            String aniversario,
            Integer pageNumber,
            Integer pageSize) {
        List<Cliente> clientes = dao.findAll();
        clientes = clientes.stream().sorted(Cliente::compareTo).collect(Collectors.toList());
        List<ClienteVO> responses = new ArrayList<>();
        clientes.forEach(cliente -> responses.add(new ClienteVO(cliente.getNome(),
                cliente.getIdade(), cliente.getSexo(), cliente.getAniversario())));
        responses.forEach(v -> v.setDataNascimento(ClienteUtil.convertAniversario(v.getDataNascimento(), v.getIdade())));
        List<ClienteVO> comId = ClienteUtil.incrementId(responses);
        ClienteParametroFiltro filtrate = new ClienteParametroFiltro(menorIdade, maiorIdade, sexo, aniversario);
        List<ClienteVO> filtrado = ClienteVOFiltro.filtro(filtrate, comId);

        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        int start = (int) pageable.getOffset();
        int end = (Math.min((start + pageable.getPageSize()), filtrado.size()));
        Page<ClienteVO> page = new PageImpl<ClienteVO>(filtrado.subList(start, end), pageable, filtrado.size());
        return new PagedResource(page);
    }

    public PagedResource findAllFromCsv(
            Integer menorIdade,
            Integer maiorIdade,
            String sexo,
            String aniversario,
            Integer pageNumber,
            Integer pageSize) {
        ClienteParametroFiltro filtrate = new ClienteParametroFiltro(menorIdade, maiorIdade, sexo, aniversario);
        List<ClienteVO> clientesVO = CsvOperations.readCsv();
        if (clientesVO == null) throw new ResourceNotFoundException("Não foi possivel recuperar os registros gravados no arquivo CSV.");
        List<ClienteVO> filtrado = ClienteVOFiltro.filtro(filtrate, clientesVO);

        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        int start = (int) pageable.getOffset();
        int end = (Math.min((start + pageable.getPageSize()), filtrado.size()));
        Page<ClienteVO> page = new PageImpl<ClienteVO>(filtrado.subList(start, end), pageable, filtrado.size());

        return new PagedResource(page);
    }

    public void delete(Integer id) {
        CsvOperations.deleteRowFromCsv(id);
    }

    public void update(ClienteVO clienteVO) {
        CsvOperations.updateRowFromCsv(clienteVO);
    }

    public void save(ClienteVO clienteVO) {
        CsvOperations.writeAClienteCsv(clienteVO);
    }
}
