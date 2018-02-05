DELIMITER $$
CREATE PROCEDURE meserosSusCuentasTotalMayorA(valor INT)   
BEGIN
    SELECT per.nombres, per.apellidos, cue.id, cue.total, sum(pdt.cantidad) as cantidadDePlatos
    FROM Empleado emp, Persona per, Cuenta cue, Pedido ped, PedidoDetalle pdt
    WHERE emp.persona = per.dni and cue.mesero = emp.id and ped.cuenta = cue.id
    	  and pdt.pedido = ped.id and cue.total > valor
    GROUP BY cue.id;
END $$
