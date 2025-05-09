package sdai.com.sis.sistema;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import sdai.com.sis.xml.XMLUtil;

/**
 * @date 08/05/2025
 * @author Sergio_M
 * @since VERSIONDCOREENCURSO
 */
@ApplicationScoped
public class DirectoriosDSistema {

    private static final String WORKSDAI = "workSDAI";
    private static final String DIREPRINCI = "DIREPRINCI";
    private static final String DIREAPLICA = "DIREAPLICA";
    private static final String DIRSAPLICA = "DIRSAPLICA";
    private static final String DIRDAPLICA = "DIRDAPLICA";
    private static final String DIRECTORIO = "DIRECTORIO";
    private static final String NAMEDIRECT = "NAMEDIRECT";
    private static final String PATH = "sistema/DIRECSISTE.xml";

    private final Map<String, Path> almacenDDirectorios;
    @Inject
    private XMLUtil xMLUtil;

    public DirectoriosDSistema() {
        this.almacenDDirectorios = new HashMap<>();
    }

    public void init() throws Exception {
        loadCreateDirectorioPrincipal();
        loadCreateDirectoriosDSistema();
    }

    private void loadCreateDirectorioPrincipal() throws Exception {
        String pathDDirectorio = getPathDirectorioPrincipal();
        Path path = Paths.get(pathDDirectorio, WORKSDAI);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }
        this.almacenDDirectorios.put(DIREPRINCI, path);
    }

    private String getPathDirectorioPrincipal() throws Exception {
        String sistemaOperativo = System.getProperty("os.name").toLowerCase();
        if (sistemaOperativo.contains("win")) {
            //TODO: Pendiente de desarrollo
            throw new Exception("Sistema operativo pendiente de desarrollo.");
        } else if (sistemaOperativo.contains("nix") || sistemaOperativo.contains("nux") || sistemaOperativo.contains("aix")) {
            File directorio = new File("/home");
            String[] names = directorio.list();
            if (names == null || names.length <= 0 || names.length > 1) {
                throw new Exception("No se ha encontrado la carpeta del usuario.");
            }
            File file = new File(directorio, names[0]);
            return file.getAbsolutePath();
        } else if (sistemaOperativo.contains("mac")) {
            //TODO: Pendiente de desarrollo
            throw new Exception("Sistema operativo pendiente de desarrollo.");
        }
        throw new Exception("Sistema operativo no conocido.");
    }

    private void loadCreateDirectoriosDSistema() throws Exception {
        Document document = xMLUtil.createDocumentoXML(PATH);
        Node root = xMLUtil.getRoot(document);
        Node nodeAplicacion = xMLUtil.getNodeDescendencia(root, DIREAPLICA);
        String directorio = nodeAplicacion.getTextContent();
        String absolutePath = getAbsolutePathDDirectorioPrincipal();
        loadCreateDirectorio(DIREAPLICA, absolutePath, directorio);
        nodeAplicacion = xMLUtil.getNodeDescendencia(root, DIRSAPLICA);
        Node[] nodes = xMLUtil.getNodesDescendencia(nodeAplicacion, DIRDAPLICA);
        for (Node node : nodes) {
            absolutePath = getAbsolutePathDDirectorioDAplicacion();
            String key = xMLUtil.getValorString(node, DIRECTORIO);
            directorio = xMLUtil.getValorString(node, NAMEDIRECT);
            loadCreateDirectorio(key, absolutePath, directorio);
        }
    }

    private void loadCreateDirectorio(String key, String absolutePath, String directorio) throws Exception {
        Path path = Paths.get(absolutePath, directorio);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }
        this.almacenDDirectorios.put(key, path);
    }

    public Path getDirectorioPrincipal() {
        Path path = this.almacenDDirectorios.get(DIREPRINCI);
        return path;
    }

    public File getFileDDirectorioPrincipal() {
        Path path = getDirectorioPrincipal();
        File file = path.toFile();
        return file;
    }

    public String getAbsolutePathDDirectorioPrincipal() {
        File file = getFileDDirectorioPrincipal();
        String absolutePath = file.getAbsolutePath();
        return absolutePath;
    }

    public Path getDirectorioDAplicacion() {
        Path path = this.almacenDDirectorios.get(DIREAPLICA);
        return path;
    }

    public File getFileDDirectorioDAplicacion() {
        Path path = getDirectorioDAplicacion();
        File file = path.toFile();
        return file;
    }

    public String getAbsolutePathDDirectorioDAplicacion() {
        File file = getFileDDirectorioDAplicacion();
        String absolutePath = file.getAbsolutePath();
        return absolutePath;
    }

}
