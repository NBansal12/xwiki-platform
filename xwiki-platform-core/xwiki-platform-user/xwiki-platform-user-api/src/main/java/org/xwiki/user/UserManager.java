/*
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.xwiki.user;

import org.xwiki.component.annotation.Role;
import org.xwiki.stability.Unstable;

/**
 * CRUD operations on users.
 *
 * @version $Id$
 * @since 12.2RC1
 */
@Unstable
@Role
public interface UserManager
{
    /**
     * @param userReference the user to retrieve and if null then retrieve the current user
     * @return the user object representing the user pointed to by the passed reference
     */
    User getUser(UserReference userReference);

    /**
     * @param userReference the reference to the user to check for existence
     * @return true if the user pointed to by the reference exists, false otherwise
     */
    boolean exists(UserReference userReference);
}