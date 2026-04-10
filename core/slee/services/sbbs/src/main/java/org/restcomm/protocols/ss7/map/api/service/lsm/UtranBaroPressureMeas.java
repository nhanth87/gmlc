/*
 * TeleStax, Open Source Cloud Communications
 * Copyright 2011-2024, TeleStax Inc. and individual contributors
 * by the @authors tag.
 *
 * This program is free software: you can redistribute it and/or modify
 * under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation; either version 3 of
 * the License, or (at your option) any later version.
 */

package org.restcomm.protocols.ss7.map.api.service.lsm;

import java.io.Serializable;

/**
 * UtranBaroPressureMeas ::= OCTET STRING (SIZE (2))
 * -- Refers to the Barometric Pressure Measurement defined in 3GPP TS 25.413.
 * <p>
 * This interface is provided locally in the GMLC project to maintain compatibility
 * with jSS7 9.2.5. The MapLsmHelper class uses reflection to safely access methods
 * returning this type, allowing the code to work with both jSS7 9.2.5 and newer versions.
 * </p>
 *
 * @author sergey vetyutnev (original jSS7 author)
 */
public interface UtranBaroPressureMeas extends Serializable {

    byte[] getData();

}
