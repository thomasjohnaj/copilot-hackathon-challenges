package example.action;

import com.opensymphony.xwork2.ActionSupport;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.ServletResponseAware;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Results({
    @Result(name = "success", type = "json", params = {"root", "resultJson"})
})
@ParentPackage("json-default")
public class ArithmeticAction extends ActionSupport implements ServletResponseAware {
    private String operation;
    private double operand1;
    private double operand2;
    private Map<String, Object> resultJson = new HashMap<>();
    private HttpServletResponse response;

    public String execute() {
        double result;

        if (operation == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resultJson.put("error", "Missing operation");
            return SUCCESS;
        }

        switch (operation) {
            case "add":
                result = operand1 + operand2;
                break;
            case "subtract":
                result = operand1 - operand2;
                break;
            case "multiply":
                result = operand1 * operand2;
                break;
            case "divide":
                result = operand1 / operand2;
                break;
            default:
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resultJson.put("error", "Invalid operation: " + operation);
                return SUCCESS;
        }

        if (Double.isInfinite(result) || Double.isNaN(result)) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resultJson.put("error", "Invalid result: " + result);
            return SUCCESS;
        }

        resultJson.put("result", result);
        return SUCCESS;
    }

    @Override
    public void setServletResponse(HttpServletResponse response) {
        this.response = response;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public double getOperand1() {
        return operand1;
    }

    public void setOperand1(double operand1) {
        this.operand1 = operand1;
    }

    public double getOperand2() {
        return operand2;
    }

    public void setOperand2(double operand2) {
        this.operand2 = operand2;
    }

    public Map<String, Object> getResultJson() {
        return resultJson;
    }
}
