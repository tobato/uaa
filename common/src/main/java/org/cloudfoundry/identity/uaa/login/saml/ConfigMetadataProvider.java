package org.cloudfoundry.identity.uaa.login.saml;

import org.opensaml.saml2.metadata.provider.AbstractMetadataProvider;
import org.opensaml.saml2.metadata.provider.MetadataProviderException;
import org.opensaml.xml.XMLObject;
import org.opensaml.xml.io.UnmarshallingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class ConfigMetadataProvider extends AbstractMetadataProvider implements ComparableProvider {

    private final Logger log = LoggerFactory.getLogger(ConfigMetadataProvider.class);

    private final String metadata;
    private final String zoneId;
    private final String alias;

    public ConfigMetadataProvider(String zoneId, String alias, String metadata) {
        this.metadata = metadata;
        this.alias = alias;
        this.zoneId = zoneId;
    }

    @Override
    protected XMLObject doGetMetadata() throws MetadataProviderException {

        InputStream stream = new ByteArrayInputStream(metadata.getBytes(StandardCharsets.UTF_8));

        try {
            return unmarshallMetadata(stream);
        } catch (UnmarshallingException e) {
            log.error("Unable to unmarshall metadata", e);
            throw new MetadataProviderException(e);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof ComparableProvider)) return false;

        ComparableProvider that = (ComparableProvider) o;

        if (!alias.equals(that.getAlias())) return false;
        if (!zoneId.equals(that.getZoneId())) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = zoneId.hashCode();
        result = 31 * result + alias.hashCode();
        return result;
    }

    @Override
    public String getAlias() {
        return alias;
    }

    @Override
    public String getZoneId() {
        return zoneId;
    }
}
