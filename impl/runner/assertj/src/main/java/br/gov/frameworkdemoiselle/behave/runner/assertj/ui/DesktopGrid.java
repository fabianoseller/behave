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
package br.gov.frameworkdemoiselle.behave.runner.assertj.ui;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

import org.apache.log4j.Logger;
import org.assertj.swing.core.MouseButton;
import org.assertj.swing.data.TableCellByColumnId;
import org.assertj.swing.data.TableCellFinder;
import org.assertj.swing.exception.ActionFailedException;
import org.assertj.swing.fixture.JTableCellFixture;
import org.assertj.swing.fixture.JTableFixture;

import br.gov.frameworkdemoiselle.behave.runner.ui.Element;
import br.gov.frameworkdemoiselle.behave.runner.ui.Grid;

public class DesktopGrid extends DesktopBase implements Grid {

	private Logger logger = Logger.getLogger(this.toString());

	@Override
	public void clickRow(String reference) {
		// Pegar a linha que tenha o texto do parâmetro referente a
		try {
			JTable table = (JTable) getElement();
			JTableFixture tFix = new JTableFixture(runner.robot, table);
			// TODO - remover se funcionar
			//TableCell cell = tFix.cell(reference);
			//tFix.click(cell, MouseButton.LEFT_BUTTON);
			tFix.click(MouseButton.LEFT_BUTTON);
		}
		catch (ActionFailedException ex) {
			JTable table = (JTable) getElement();
			JTableFixture tFix = new JTableFixture(runner.robot, table);
			
			for (int i = 0; i < tFix.target().getModel().getRowCount(); i++) {
				for (int j = 0; j < tFix.target().getModel().getColumnCount(); j++) {
					JPanel panel = (JPanel) tFix.target().getModel().getValueAt(i, j);

					for (Component c : panel.getComponents()) {
						if (c instanceof JLabel && ((JLabel) c).getText().equals(reference)) {
							// Seleciona a linha
							tFix.selectRows(i);
							logger.debug("Linha com valor [" + reference + "] encontada na posição [" + i + "] na coluna [" + j + "].");
						}
					}
				}
			}
		}
	}

	@Override
	public String findLastLine(Element tabela) {
		JTable table = (JTable) getElement();
		return "" + table.getRowCount();
	}

	@Override
	public String findTextInTable(Element element, String l, String c) {
		return getTableCellFixtureByRowColumn(l, c).value();
	}

	@Override
	public void tableButtonClick(Element element, String l, String c) {
		getTableCellFixtureByRowColumn(l, c).click();
	}

	@Override
	public void tableSelectClick(String value, String l, String c, Element element) {
		getTableCellFixtureByRowColumn(l, c).select().click();
	}

	@Override
	public void tableTextSendKeys(String value, String l, String c, Element element) {
		getTableCellFixtureByRowColumn(l, c).enterValue(value);
	}

	private JTableCellFixture getTableCellFixtureByRowColumn(String l, String c) {
		JTable table = (JTable) getElement();
		JTableFixture tableFix = new JTableFixture(runner.robot, table);

		int row = -1;
		String columnName;

		try {
			row = Integer.parseInt(l) - 1;
		}
		catch (Exception e) {
			row = tableFix.cell(l).row();
		}

		try {
			columnName = table.getColumnName(Integer.parseInt(c) - 1);
		}
		catch (Exception e) {
			columnName = c;
		}

		TableCellFinder tableCellFinder = TableCellByColumnId.row(row).columnId(columnName);
		JTableCellFixture cellFix = tableFix.cell(tableCellFinder);
		return cellFix;
	}
}