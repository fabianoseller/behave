/*
 * Demoiselle Framework
 * Copyright (C) 2013 SERPRO
 * ----------------------------------------------------------------------------
 * This file is part of Demoiselle Framework.
 * 
 * Demoiselle Framework is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public License version 3
 * as published by the Free Software Foundation.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License version 3
 * along with this program; if not,  see <http://www.gnu.org/licenses/>
 * or write to the Free Software Foundation, Inc., 51 Franklin Street,
 * Fifth Floor, Boston, MA  02110-1301, USA.
 * ----------------------------------------------------------------------------
 * Este arquivo é parte do Framework Demoiselle.
 * 
 * O Framework Demoiselle é um software livre; você pode redistribuí-lo e/ou
 * modificá-lo dentro dos termos da GNU LGPL versão 3 como publicada pela Fundação
 * do Software Livre (FSF).
 * 
 * Este programa é distribuído na esperança que possa ser útil, mas SEM NENHUMA
 * GARANTIA; sem uma garantia implícita de ADEQUAÇÃO a qualquer MERCADO ou
 * APLICAÇÃO EM PARTICULAR. Veja a Licença Pública Geral GNU/LGPL em português
 * para maiores detalhes.
 * 
 * Você deve ter recebido uma cópia da GNU LGPL versão 3, sob o título
 * "LICENCA.txt", junto com esse programa. Se não, acesse <http://www.gnu.org/licenses/>
 * ou escreva para a Fundação do Software Livre (FSF) Inc.,
 * 51 Franklin St, Fifth Floor, Boston, MA 02111-1301, USA.
 */
package br.gov.frameworkdemoiselle.behave.samplegoogle;

import java.util.ArrayList;

import org.junit.Test;

import br.gov.frameworkdemoiselle.behave.controller.EngineController;
import br.gov.frameworkdemoiselle.behave.samplegoogle.steps.MySteps;
import br.gov.frameworkdemoiselle.behave.util.FileUtil;

public class AppTest {

	@Test
	public void start() throws Throwable {
		// Cria o array contendo as histórias
		ArrayList<String> stories = new ArrayList<String>();
		stories.add(FileUtil.getAbsolutePath(".") + "/stories");

		// Instância o motor de testes
		EngineController eng = new EngineController();
		// Adiciona passos (sentenças) específicas
		eng.addSteps(new MySteps());
		// Manda rodar informando as histórias
		eng.run(stories);
	}

	public static void main(String[] args) throws Throwable {
		AppTest app = new AppTest();
		app.start();
	}

}