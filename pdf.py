


def write_fillable_pdf(input_pdf_path, output_pdf_path, data_dict):
    template_pdf = pdfrw.PdfReader(input_pdf_path)
    
    annotations = template_pdf.pages[0][ANNOT_KEY]
    for annotation in annotations:
        if annotation[SUBTYPE_KEY] == WIDGET_SUBTYPE_KEY:
            if annotation[ANNOT_FIELD_KEY]:
                key = annotation[ANNOT_FIELD_KEY][1:-1]
                print(key)
                if key in data_dict.keys():
                  annotation.update(
                      pdfrw.PdfDict(V='{}'.format(data_dict[key]))
                    # pdfrw.PdfDict(V=data_dict[key])
                  )
    # print(template_pdf)
    pdfrw.PdfWriter().write(output_pdf_path, template_pdf)
    # form = io.BytesIO()
    # pdfrw.PdfWriter().write(form, template_pdf)
    # with open(output_pdf_path, 'wb') as f:
    #   f.write(form.read())

write_fillable_pdf(INVOICE_TEMPLATE_PATH, INVOICE_OUTPUT_PATH,result_obj)
