import org.apache.commons.lang.StringUtils;

/**
 * Created by huanwuji on 2014/11/16.
 */
public class CodeTemplate {
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

    public CodeTemplate _(String template, String... values) {
        indent().append(format(template, values)).append(";\n");
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
