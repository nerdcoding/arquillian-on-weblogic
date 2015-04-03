/*
 * PersonServiceIT.java
 *
 * Copyright (c) 2015, Tobias Koltsch. All rights reserved.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/lgpl>.
 */

package org.nerdcoding.sample.arquillian.weblogic.ejb;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;

import org.junit.Test;
import org.nerdcoding.sample.arquillian.weblogic.AbstractArquillianIT;
import org.nerdcoding.sample.arquillian.weblogic.jpa.Person;

/**
 * Arquillian based integration test for {@link PersonService} EJB.
 *
 * @author Tobias Koltsch
 */
public class PersonServiceIT extends AbstractArquillianIT {

    @EJB
    private PersonService personService;

    @Test
    public void testCreatePerson() {
        Person person = personService.createPerson("Sheldon", "Cooper", new Date());
        assertNotNull(person);
        assertNotNull(person.getId());
    }


    @Test
    public void testFindByLastName() {
        personService.createPerson("Johnny", "Cooper", new Date());
        personService.createPerson("Alice", "Cooper", new Date());
        personService.createPerson("Milhouse", "Van Houten", new Date());
        personService.createPerson("Luke", "Skywalker", new Date());

        List<Person> found = personService.findByLastName("Cooper");
        assertEquals(2, found.size());

        found = personService.findByLastName("Skywalker");
        assertEquals(1, found.size());
    }

}
