package com.ipartek.formacion.medicamentos.controladores;

import com.ipartek.formacion.medicamentos.accesodatos.Dao;
import com.ipartek.formacion.medicamentos.accesodatos.MedicamentoDaoMySql;
import com.ipartek.formacion.medicamentos.modelos.Medicamento;

public class Configuracion {

	public static Dao<Medicamento> daoMedicamentos = MedicamentoDaoMySql.getInstancia();

}
