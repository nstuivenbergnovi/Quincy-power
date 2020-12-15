package com.quincy.database_test.controller;

import ch.qos.logback.core.net.server.Client;
import com.quincy.database_test.model.Customer;
import com.quincy.database_test.model.Invoice;
import com.quincy.database_test.model.InvoiceLine;
import com.quincy.database_test.model.Product;
import com.quincy.database_test.service.CustomerService;
import com.quincy.database_test.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/factura")
@SessionAttributes("customer")
public class InvoiceController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/Invoice/{customerId}")
    public String crear(@PathVariable(value = "customerId") Long customerId, Map<String, Object> model, RedirectAttributes flash) {
        Client client = customerService.findOne(customerId);
        if (client == null) {
            flash.addFlashAttribute("error", "Customer doesn't exist");
            return "redirect:/clients";
        }
        Customer customer = new Customer();
        invoice.setCustomer(customer);
        model.put("invoice", invoice);
        model.put("title", "Crear factura");

        return "/invoices/form";
    }

    @GetMapping(value = "/cargar-producto/{search}", produces = {"application/json"})
    public @ResponseBody
    List<Product> loadProducts(@PathVariable String search) {
        return custormerService.findByName(search);
    }

    @PostMapping("/form")
    public String save(
            @Valid Invoice invoice,
            BindingResult result,
            @RequestParam(name = "item_id[]", required = false) Long[] itemId,
            @RequestParam(name = "quantity[]", required = false) Integer[] quantities,
            RedirectAttributes flash,
            SessionStatus status,
            Model model) {

        if (result.hasErrors()) {
            model.addAttribute("title", "Crear Factura");
            return "/invoices/form";
        }
        if (itemId == null || itemId.length == 0) {
            model.addAttribute("title", "Crear Factura");
            model.addAttribute("error", "La factura debe tener productos");
            return "/invoices/form";
        }
        for (int i = 0; i < itemId.length; i++) {
            Product product = ProductService.getProductById(itemId[i]);
            InvoiceLine line = new InvoiceLine();
            line.setQuantity(quantities[i]);
            line.setProduct(product);
            invoice.addLine(line);
        }
        customerService.saveInvoice(invoice);
        status.setComplete();
        flash.addFlashAttribute("success", "Factura creada con éxito");
        return "redirect:/ver/" + invoice.getCustomer().getId();
    }

    @GetMapping("/ver/{id}")
    public String ver(@PathVariable(value = "id") Long id,
                      Model model,
                      RedirectAttributes flash) {
        //Invoice invoice = clientService.findInvoiceById(id);
        Invoice invoice = customerService.fetchByIdWithClientWithInvoiceLineWithProduct(id);
        if (invoice == null) {
            flash.addFlashAttribute("error", "La factura no existe");
            return "redirect:/clientes";
        }
        model.addAttribute("invoice", invoice);
        model.addAttribute("title", "Factura: ".concat(invoice.getDescription()));
        return "/invoices/view";
    }

    @GetMapping("/delete/{id}")
    public String delete(
            @PathVariable(value = "id") Long id,
            RedirectAttributes flash) {
        Invoice invoice = customerService.findInvoiceById(id);
        if (invoice != null) {
            customerService.deleteInvoice(id);
            flash.addFlashAttribute("success", "Factura eliminada con éxito");
            return "redirect:/ver/" + invoice.getClient().getId();
        } else {
            flash.addAttribute("error", "La factura no existe");
            return "redirect:/clientes";
        }
    }
}
