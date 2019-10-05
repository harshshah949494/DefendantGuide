import doc_generator
import email_generator
import datetime

def send_checklist(checklist, email):
    generated_doc_name = doc_generator.generate_checklist_doc(checklist)
    email_sent_status = False
    if generated_doc_name is not None:
        subject = "Evidence & Witness CheckList"
        body = "PFA your checklist document"
        email_sent_status = email_generator.send_email(generated_doc_name,email,subject,body)
    return email_sent_status
        
    
def send_transcript(transcript, email):
    generated_transcript_name = doc_generator.generate_transcript(transcript)
    if generated_transcript_name is not None:
        subject = "Transcript of your conversation with Defendent Guide on "+ str(datetime.datetime.now())
        body = ""
        email_sent_status = email_generator.send_email(generated_transcript_name,email,subject,body)
    return email_sent_status

def parse_form_fields(form_fields, email):
    form_filled_name = doc_generator.fill_pdf(form_fields)
    if form_filled_name is not None:
        subject = "Case Form Filled"
        body = "PFA your filled form document"
        email_sent_status = email_generator.send_email(form_filled_name,email,subject,body)
    return email_sent_status
    
