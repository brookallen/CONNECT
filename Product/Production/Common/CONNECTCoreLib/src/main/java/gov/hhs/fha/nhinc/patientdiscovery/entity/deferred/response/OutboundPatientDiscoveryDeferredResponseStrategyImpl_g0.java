/*
 * Copyright (c) 2012, United States Government, as represented by the Secretary of Health and Human Services. 
 * All rights reserved. 
 *
 * Redistribution and use in source and binary forms, with or without 
 * modification, are permitted provided that the following conditions are met: 
 *     * Redistributions of source code must retain the above 
 *       copyright notice, this list of conditions and the following disclaimer. 
 *     * Redistributions in binary form must reproduce the above copyright 
 *       notice, this list of conditions and the following disclaimer in the documentation 
 *       and/or other materials provided with the distribution. 
 *     * Neither the name of the United States Government nor the 
 *       names of its contributors may be used to endorse or promote products 
 *       derived from this software without specific prior written permission. 
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND 
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED 
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE 
 * DISCLAIMED. IN NO EVENT SHALL THE UNITED STATES GOVERNMENT BE LIABLE FOR ANY 
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES 
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; 
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND 
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT 
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS 
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE. 
 */
package gov.hhs.fha.nhinc.patientdiscovery.entity.deferred.response;

import gov.hhs.fha.nhinc.orchestration.Orchestratable;
import gov.hhs.fha.nhinc.orchestration.OrchestrationStrategy;
import gov.hhs.fha.nhinc.patientdiscovery.nhin.deferred.response.proxy.NhinPatientDiscoveryDeferredRespProxy;
import gov.hhs.fha.nhinc.patientdiscovery.nhin.deferred.response.proxy.NhinPatientDiscoveryDeferredRespProxyObjectFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hl7.v3.MCCIIN000002UV01;

/**
 * @author akong
 *
 */
public class OutboundPatientDiscoveryDeferredResponseStrategyImpl_g0 implements OrchestrationStrategy {

    private static Log log = LogFactory.getLog(OutboundPatientDiscoveryDeferredResponseStrategyImpl_g0.class);

    public OutboundPatientDiscoveryDeferredResponseStrategyImpl_g0() {
    }

    protected Log getLogger() {
        return log;
    }

    @Override
    public void execute(Orchestratable message) {
        if (message instanceof OutboundPatientDiscoveryDeferredResponseOrchestratable) {
            execute((OutboundPatientDiscoveryDeferredResponseOrchestratable) message);
        } else {
            getLogger().error("Not an OutboundPatientDiscoveryDeferredResponseOrchestratable.");
        }
    }

    public void execute(OutboundPatientDiscoveryDeferredResponseOrchestratable message) {
        getLogger().debug("Begin OutboundPatientDiscoveryDeferredResponseOrchestratableImpl_g0.process");
        if (message == null) {
            getLogger().debug("OutboundPatientDiscoveryDeferredResponseOrchestratable was null");
            return;
        }

        if (message instanceof OutboundPatientDiscoveryDeferredResponseOrchestratable) {
            NhinPatientDiscoveryDeferredRespProxy nhinPatientDiscovery = new NhinPatientDiscoveryDeferredRespProxyObjectFactory()
                    .getNhinPatientDiscoveryAsyncRespProxy();

            MCCIIN000002UV01 response = nhinPatientDiscovery.respondingGatewayPRPAIN201306UV02(message.getRequest(),
                    message.getAssertion(), message.getTarget());
            message.setResponse(response);
        } else {
            getLogger().error(
                    "OutboundPatientDiscoveryDeferredResponseStrategyImpl_g0 received a message "
                            + "which was not of type OutboundPatientDiscoveryDeferredResponseOrchestratable.");
        }
        getLogger().debug("End OutboundPatientDiscoveryDeferredResponseStrategyImpl_g0.process");
    }
}