package sdai.com.sis.xml;

/**
 * @date 26/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public final class BuildDocumentoXML {

    private final StringBuilder stringBuilder;

    public BuildDocumentoXML() {
        this.stringBuilder = new StringBuilder();
    }

    public void createCabecera() {
        this.stringBuilder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        this.stringBuilder.append("\n");
    }

    public void createRoot() {
        this.stringBuilder.append("<ROOT>");
        this.stringBuilder.append("\n");
    }

    public void createNode(String tagName) {
        this.stringBuilder.append("<");
        this.stringBuilder.append(tagName);
        this.stringBuilder.append(">");
        this.stringBuilder.append("\n");
    }

    public void createNode(String tagName, String value) {
        this.stringBuilder.append("<");
        this.stringBuilder.append(tagName);
        this.stringBuilder.append(">");
        this.stringBuilder.append(value);
        this.stringBuilder.append("</");
        this.stringBuilder.append(tagName);
        this.stringBuilder.append(">");
        this.stringBuilder.append("\n");
    }

    public void closeNode(String tagName) {
        this.stringBuilder.append("</");
        this.stringBuilder.append(tagName);
        this.stringBuilder.append(">");
        this.stringBuilder.append("\n");
    }

    public void closeRoot() {
        this.stringBuilder.append("</ROOT>");
        this.stringBuilder.append("\n");
    }

    public String getDocumentoXML() {
        return this.stringBuilder.toString();
    }

}
