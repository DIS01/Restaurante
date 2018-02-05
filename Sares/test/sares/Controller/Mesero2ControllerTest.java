/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sares.Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.util.Date;
import java.util.LinkedList;
import static junit.framework.Assert.assertEquals;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import sares.Model.Categoria;
import sares.Model.Conexion;
import sares.Model.Cuenta;
import sares.Model.Item;
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
    LinkedList<Item> platillosEntrada;
    LinkedList<Item> platosFuertes;
    Mesero mesero1;
    Mesa mesa1;
    
    public Mesero2ControllerTest(){
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        Connection conexionConDB = Conexion.getConexion();  
        platillosEntrada= Categoria.getItems(new Categoria("Platillos de entrada"));
        platosFuertes = Categoria.getItems(new Categoria("Platos Fuerte"));
        mesero1= new Mesero("0876545678","Mauricio","Leiton","Data de villamil");
        mesa1= new Mesa(1);
    }

    @After
    public void tearDown() throws Exception {
    }
    
    @Test
    public void PruebaPedidosTest1() throws SQLException, IOException, ParseException {
        //int id, boolean pagada, Float total, Mesa mesa, boolean prioridad, Mesero mesero
        Cuenta cuenta1=new Cuenta(1,false,0.0f,mesa1,false,mesero1);
        
        //int id, Time horaingreso, float tiempoestimado, String estado, Cuenta cuenta, Date fecha
        Pedido pedido1=new Pedido(1,new Time(12,12,12),0.0f,"en cola",cuenta1,new Date(2018,12,1,12,12,12));
        
        //int id, Pedido pedido, Item item, float precio, int cantidad, String detalle
        LinkedList<PedidoDetalle> listapedidos=new LinkedList<>();
        listapedidos.add(new PedidoDetalle(1,pedido1,platillosEntrada.get(0),7.0f, 1,"sin cebolla"));
        listapedidos.add(new PedidoDetalle(2,pedido1,platillosEntrada.get(1),5.0f, 1,"con cebolla"));
        listapedidos.add(new PedidoDetalle(3,pedido1,platillosEntrada.get(2),5.0f, 1,"con salsa de tomate"));
        
        pedido1.setPedidosDetalles(listapedidos);
        pedido1.actualizarTiempoEstimado();
        
        float esperadoValor = 26.0f;
        assertEquals(esperadoValor, pedido1.actualizarTiempoEstimado());
    }
    
    @Test
    public void PruebaPedidosTest2() throws SQLException, IOException, ParseException {
        //int id, boolean pagada, Float total, Mesa mesa, boolean prioridad, Mesero mesero
        Cuenta cuenta2=new Cuenta(2,false,0.0f,mesa1,false,mesero1);
        
        //int id, Time horaingreso, float tiempoestimado, String estado, Cuenta cuenta, Date fecha
        Pedido pedido2=new Pedido(2,new Time(12,12,12),0.0f,"en cola",cuenta2,new Date(2018,12,1,12,12,12));
        
        //int id, Pedido pedido, Item item, float precio, int cantidad, String detalle
        LinkedList<PedidoDetalle> listapedidos=new LinkedList<>();
        listapedidos.add(new PedidoDetalle(4,pedido2,platillosEntrada.get(3),7.0f, 1,"sin cebolla"));
        listapedidos.add(new PedidoDetalle(5,pedido2,platillosEntrada.get(4),5.0f, 1,"con cebolla"));
        
        pedido2.setPedidosDetalles(listapedidos);
        pedido2.actualizarTiempoEstimado();
        
        float esperadoValor = 20.0f;
        assertEquals(esperadoValor, pedido2.actualizarTiempoEstimado());
    }
    
    @Test
    public void PruebaPedidosTest3() throws SQLException, IOException, ParseException {
        //int id, boolean pagada, Float total, Mesa mesa, boolean prioridad, Mesero mesero
        Cuenta cuenta3=new Cuenta(3,false,0.0f,mesa1,false,mesero1);
        
        //int id, Time horaingreso, float tiempoestimado, String estado, Cuenta cuenta, Date fecha
        Pedido pedido3=new Pedido(3,new Time(12,12,12),0.0f,"en cola",cuenta3,new Date(2018,12,1,12,12,12));
        
        //int id, Pedido pedido, Item item, float precio, int cantidad, String detalle
        LinkedList<PedidoDetalle> listapedidos=new LinkedList<>();
        listapedidos.add(new PedidoDetalle(6,pedido3,platosFuertes.get(0),7.0f, 1,"sin cebolla"));
        listapedidos.add(new PedidoDetalle(7,pedido3,platosFuertes.get(1),5.0f, 1,"con cebolla"));
        listapedidos.add(new PedidoDetalle(8,pedido3,platosFuertes.get(2),5.0f, 1,"con cebolla"));
        
        pedido3.setPedidosDetalles(listapedidos);
        pedido3.actualizarTiempoEstimado();
        
        float esperadoValor = 21.0f;
        assertEquals(esperadoValor, pedido3.actualizarTiempoEstimado());
    }
   
    @Test
    public void PruebaPedidosTest4() throws SQLException, IOException, ParseException {
        //int id, boolean pagada, Float total, Mesa mesa, boolean prioridad, Mesero mesero
        Cuenta cuenta4=new Cuenta(4,false,0.0f,mesa1,false,mesero1);
        
        //int id, Time horaingreso, float tiempoestimado, String estado, Cuenta cuenta, Date fecha
        Pedido pedido4=new Pedido(4,new Time(12,12,12),0.0f,"en cola",cuenta4,new Date(2018,12,1,12,12,12));
        
        //int id, Pedido pedido, Item item, float precio, int cantidad, String detalle
        LinkedList<PedidoDetalle> listapedidos=new LinkedList<>();
        listapedidos.add(new PedidoDetalle(6,pedido4,platosFuertes.get(3),7.0f, 1,"sin cebolla"));
        
        pedido4.setPedidosDetalles(listapedidos);
        pedido4.actualizarTiempoEstimado();
        
        float esperadoValor = 30.0f;
        assertEquals(esperadoValor, pedido4.actualizarTiempoEstimado());
    }
    /**
     * Test of initialize method, of class Mesero2Controller.
     */
    @Test
    public void testInitialize(){
       
    }

    

}
