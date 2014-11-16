import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.PropertyDescriptor;

/**
 * Created by huanwuji on 2014/11/16.
 */
public class CodeTemplate {
    private static Logger logger = LoggerFactory.getLogger(CodeTemplate.class);
    private StringBuilder sb;
    private int indent = 0;

    private CodeTemplate(StringBuilder sb) {
        this.sb = sb;
    }

    public static CodeTemplate create() {
        return new CodeTemplate(new StringBuilder());
    }

    public CodeTemplate $class(String template, String... values) {
        return $begin(template, values);
    }

    public CodeTemplate $$class() {
        return end();
    }

    public CodeTemplate $def(String template, String... values) {
        return $begin(template, values);
    }

    public CodeTemplate $$def() {
        return end();
    }

    private CodeTemplate $begin(String template, String... values) {
        indent().append(format(template, values)).append(" {\n");
        indent++;
        return this;
    }

    public CodeTemplate c(String template, String... values) {
        indent().append(format(template, values)).append(";\n");
        return this;
    }

    public CodeTemplate c(String template, String[] names, String[] values) {
        indent().append(format(template, names, values)).append(";\n");
        return this;
    }

    public CodeTemplate $end() {
        end();
        return this;
    }

    public CodeTemplate $if(String express, String... values) {
        indent().append("if (").append(format(express, values)).append(") {\n");
        indent++;
        return this;
    }

    public CodeTemplate $else(String express, String... values) {
        indent--;
        indent().append("} else {\n");
        indent++;
        return this;
    }

    public CodeTemplate $elseIf(String express, String... values) {
        indent--;
        indent().append("} else if (").append(format(express, values)).append(" ) {\n");
        indent++;
        return this;
    }

    public CodeTemplate $$if() {
        return end();
    }

    public CodeTemplate $for(String express, String... values) {
        indent().append("for (").append(format(express, values)).append(") {\n");
        indent++;
        return this;
    }

    public CodeTemplate $$for() {
        return end();
    }

    private CodeTemplate end() {
        indent--;
        indent().append("}\n");
        return this;
    }

    public CodeTemplate getValue(Class clazz, String name, String varName, String template) {
        String _name = StringUtils.substringBefore(name, ".");
        PropertyDescriptor descriptor = getDescriptor(clazz, _name);
        String propValueVarName = varName + "_" + _name;
        c("$class $propValueVarName = $getValue()",
                descriptor.getPropertyType().getName(), propValueVarName, varName + "." + descriptor.getReadMethod().getName())
                .$if("$propValueVarName != null", propValueVarName);
        String afterName = StringUtils.substringAfter(name, ".");
        if (StringUtils.isEmpty(afterName)) {
            c(template, new String[]{"\\$propValueVarName", "\\$propValueType"}, new String[]{propValueVarName, descriptor.getPropertyType().getName()});
        } else {
            getValue(descriptor.getPropertyType(), afterName, propValueVarName, template);
        }
        $$if();
        return this;
    }

    private static PropertyDescriptor getDescriptor(Class clazz, String name) {
        PropertyDescriptor propertyDescriptor = null;
        try {
            propertyDescriptor = PropertyUtils.getPropertyDescriptor(clazz.newInstance(), name);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(), e.getLocalizedMessage());
        }
        return propertyDescriptor;
    }

    private String format(String template, String[] names, String[] values) {
        String result = template;
        for (int i = 0; i < names.length; i++) {
            result = result.replaceAll(names[i], values[i]);
        }
        return result;
    }

    private String format(String template, String... values) {
        String result = template;
        for (String value : values) {
            result = result.replaceFirst("\\$\\w+", value);
        }
        return result;
    }

    public void print() {
        System.out.println(sb.toString());
    }

    @Override
    public String toString() {
        return sb.toString();
    }

    public StringBuilder indent() {
        return sb.append(StringUtils.repeat(" ", 4 * indent));
    }
}
