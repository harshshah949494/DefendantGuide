# from dict: generate a doc/pdf
from fpdf import FPDF
import random
import os
import io
import pdfrw
from constants import * 

def generate_checklist_doc(checklist):
    filename = checklist_pdf_genertor("Checklist for Trial", "checklist", checklist )
    return filename

def generate_transcript(transcript):
    filename = transcript_pdf_generator("Transcript of your conversation", "transcript", transcript)
    return filename

def transcript_pdf_generator(title, filetype, data):
    if not data:
        return
    filename = filetype + str(random.randint(1000,9999)) + ".pdf"
    i = 1
    pdf = FPDF()
    pdf.add_page()
    for entry in data:
        for key, val in entry.items():
            pdf.set_font("Arial", size=16)
            if(key == "user"):
                pdf.set_text_color(0,0,200)
                pdf.cell(200, 10, txt = "You:" + val, ln=i)
            else:
                pdf.set_text_color(0,200,0)
                pdf.cell(200, 10, txt = "App:" + val, ln=i)
        pdf.cell(200, 10, txt = "", ln=i)
    pdf.output(filename)
    return filename


def checklist_pdf_genertor(title , filetype, data = None):
    if not data:
        return
    
    filename = filetype + str(random.randint(1000,9999)) + ".pdf" # Can be timestamp
    pdf = FPDF()
    pdf.add_page()
    i = 1
    pdf.set_font("Arial", size=25,style='B')
    pdf.cell(200, 10, txt= title, ln=i)
    i = i+1
    for key, vals in data.items():
        pdf.set_font("Arial", size=16,style='B')
        pdf.cell(200, 10, txt= key, ln=i)
        i = i+2
        j = 1
        for val in vals:
            pdf.set_font("Arial", size=12)
            pdf.cell(200, 10, txt = str(j) + ". " + val, ln=i)
            i = i+1
            j = j+1
    pdf.output(filename )
    return filename

def fill_pdf(data_dict):
    template_pdf = pdfrw.PdfReader(Defendant_Form_TEMPLATE_PATH)
    
    annotations = template_pdf.pages[0][ANNOT_KEY]
    for annotation in annotations:
        if annotation[SUBTYPE_KEY] == WIDGET_SUBTYPE_KEY:
            if annotation[ANNOT_FIELD_KEY]:
                key = annotation[ANNOT_FIELD_KEY][1:-1]
                if key in data_dict.keys():
                  annotation.update(
                      pdfrw.PdfDict(V='{}'.format(data_dict[key]))
                    # pdfrw.PdfDict(V=data_dict[key])
                  )
    # print(template_pdf)
    output_filename = Filled_Form_OUTPUT_PATH #+ str(random.randint(1000,9999))
    pdfrw.PdfWriter().write(output_filename, template_pdf)
    return output_filename
