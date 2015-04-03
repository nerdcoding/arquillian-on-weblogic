/*
 * PersonService.java
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

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.nerdcoding.sample.arquillian.weblogic.jpa.Person;

/**
 * Small EJB does some CRUD logic on the {@link Person} entity.
 *
 * @author Tobias Koltsch
 */
@Stateless
public class PersonService {

    @PersistenceContext
    private EntityManager entityManager;

    public Person createPerson(final String firstName, final String lastName, final Date dayOfBirth) {
        final Person person = new Person();
        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setDayOfBirth(dayOfBirth);

        entityManager.persist(person);

        return person;
    }

    public List<Person> findByFirstName(final String firstName) {
        TypedQuery<Person> query = entityManager.createQuery("select p from Person p where p.firstName = :firstName", Person.class);
        query.setParameter("firstName", firstName);

        return query.getResultList();
    }
}
