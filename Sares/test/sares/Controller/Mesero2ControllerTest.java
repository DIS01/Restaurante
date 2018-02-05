/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sares.Controller;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Date;
import java.util.LinkedList;
import static junit.framework.Assert.assertEquals;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import sares.Model.Categoria;
import sares.Model.Cuenta;
import sares.Model.Mesa;
import sares.Model.Mesero;
import sares.Model.Pedido;
import sares.Model.PedidoDetalle;
import sares.Model.Platillo;

/**
 *
 * @author mdleiton
 */
public class Mesero2ControllerTest {
    
    public Mesero2ControllerTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
    private void PruebaPedidosTest1() throws SQLException, IOException {
        Categoria categoria1= new Categoria("Platos Fuerte");
        //float tiempoEstimado, int id, float valor, String nombre, boolean activo, Categoria categoria, float Stock
        Platillo plato1=new Platillo(20.0f,1,7.0f,"arroz marinero",true,categoria1,5.0f);
        Platillo plato2=new Platillo(18.0f,2,5.0f,"arroz con camaron",true,categoria1,5.0f);
        Platillo plato3=new Platillo(15.0f,3,5.0f,"arroz con concha",true,categoria1,5.0f);
        
        Mesa mesa1 = new Mesa(1);
        //String dni, String nombres, String apellidos, String domicilio
        Mesero mesero1= new Mesero("0876545678","Mauricio","Leiton","Data de villamil");
        //int id, boolean pagada, Float total, Mesa mesa, boolean prioridad, Mesero mesero
        Cuenta cuenta1=new Cuenta(1,false,0.0f,mesa1,false,mesero1);
        
        //int id, Time horaingreso, float tiempoestimado, String estado, Cuenta cuenta, Date fecha
        Pedido pedido1=new Pedido(1,new Time(12,12,12),0.0f,"en cola",cuenta1,new Date(2018,12,1,12,12,12));
        
        //int id, Pedido pedido, Item item, float precio, int cantidad, String detalle
        PedidoDetalle pd1=new PedidoDetalle(1,pedido1,plato1,7.0f, 1,"sin cebolla");
        PedidoDetalle pd2=new PedidoDetalle(2,pedido1,plato2,5.0f, 1,"con cebolla");
        PedidoDetalle pd3=new PedidoDetalle(3,pedido1,plato3,5.0f, 1,"con salsa de tomate");
        
        LinkedList<PedidoDetalle> listapedidos=new LinkedList<>();
        listapedidos.add(pd1);
        listapedidos.add(pd2);
        listapedidos.add(pd3);
        pedido1.setPedidosDetalles(listapedidos);
        pedido1.actualizarTiempoEstimado();
        
       float esperadoValor = 26.0f;
       assertEquals(esperadoValor, pedido1.actualizarTiempoEstimado());
    }

    /**
     * Test of initialize method, of class Mesero2Controller.
     */
    @Test
    public void testInitialize() throws SQLException, IOException {
       PruebaPedidosTest1();
    }

    

}
