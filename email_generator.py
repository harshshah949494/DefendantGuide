import smtplib
from email.mime.multipart import MIMEMultipart
from email.mime.application import MIMEApplication
from email import encoders

def send_email(doc_name,to_email,subject,body):
    # todos
    from_email = "hackoverflow22@gmail.com"
    password = "hackoverflow@12"

    s = smtplib.SMTP(host='smtp.gmail.com', port=587)
    s.starttls()
    s.login(from_email, password)

    msg = MIMEMultipart()
    msg['Subject'] = subject
    msg['From'] = from_email
    msg['To']= to_email
    # fo = open(doc_name + ".pdf",'rb')
    fo = open(doc_name,'rb')
    attach = MIMEApplication(fo.read(),_subtype="pdf")
    fo.close()

    attach.add_header('Content-Disposition','attachment',filename=doc_name)
    msg.attach(attach)
    
    s.send_message(msg)
    print("Sending to ",to_email)    
    
    return True
