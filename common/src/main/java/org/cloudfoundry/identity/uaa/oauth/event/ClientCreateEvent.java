/*******************************************************************************
 *     Cloud Foundry 
 *     Copyright (c) [2009-2014] Pivotal Software, Inc. All Rights Reserved.
 *
 *     This product is licensed to you under the Apache License, Version 2.0 (the "License").
 *     You may not use this product except in compliance with the License.
 *
 *     This product includes a number of subcomponents with
 *     separate copyright notices and license terms. Your use of these
 *     subcomponents is subject to the terms and conditions of the
 *     subcomponent's license, as noted in the LICENSE file.
 *******************************************************************************/

package org.cloudfoundry.identity.uaa.oauth.event;

import java.security.Principal;

import org.cloudfoundry.identity.uaa.audit.AuditEvent;
import org.cloudfoundry.identity.uaa.audit.AuditEventType;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.ClientDetails;

/**
 * @author Dave Syer
 * 
 */
public class ClientCreateEvent extends AbstractClientAdminEvent {

    public ClientCreateEvent(ClientDetails client, Authentication principal) {
        super(client, principal);
    }

    @Override
    public AuditEvent getAuditEvent() {
        return createAuditRecord(getClient().getClientId(), AuditEventType.ClientCreateSuccess,
                        getOrigin(getPrincipal()));
    }

}
