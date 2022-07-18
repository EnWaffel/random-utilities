package de.gml;

import de.enwaffel.randomutils.file.FileOrPath;
import de.enwaffel.randomutils.file.FileUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.OutputStream;
import java.util.HashMap;

public class GML {

    public static void main(String[] args) throws Exception {
        HashMap<String, String> _args = new HashMap<>();

        for (int i = 0;i < args.length;i++) {
            String str = args[i];
            if (str.contains("-")) {
                String argName = str.replaceAll("-", "");
                String arg = args.length > (i + 1) ? args[i + 1] : "";
                _args.put(argName, arg);
            }
        }
        String dir = _args.getOrDefault("dir", "");
        if (dir.equalsIgnoreCase("CUR")) dir = "";

        if (_args.containsKey("setup")) {
            if (_args.get("setup").equals("new")) {
                new File(dir).mkdirs();
                String setupDir = dir + File.separator;
                if (!_args.containsKey("force")) {
                    if (new File(setupDir + "proj.xml").exists()) {
                        System.err.println("Project found in folder");
                        return;
                    }
                }
                new File(setupDir + "src").mkdirs();

                Document doc = newXML();
                Element root = doc.createElement("project");
                doc.appendChild(root);
                root.appendChild(doc.createElement("dependencies"));

                saveXML(doc, FileUtil.getOutputStream(FileOrPath.path(setupDir + "proj.xml")));
            }
        } else if (_args.containsKey("run")) {
            String _dir = dir + File.separator;
            if (!new File(_dir + "proj.xml").exists()) {
                System.err.println("\"proj.xml\" not found");
            }
        }
    }

    public static Document newXML() throws ParserConfigurationException {
        return DocumentBuilderFactory.newDefaultInstance().newDocumentBuilder().newDocument();
    }

    public static void saveXML(Document doc, OutputStream stream) throws TransformerException {
        Transformer transformer = TransformerFactory.newDefaultInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(stream);
        transformer.transform(source, result);
    }

}
