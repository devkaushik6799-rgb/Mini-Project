-- H2-compatible sample catalogue.
-- Safe to execute more than once: each row is inserted only if its name is absent.
INSERT INTO medicines(name,category,description,price,stock_quantity,prescription_required)
SELECT 'Paracetamol 500 mg','Pain Relief','Common over-the-counter pain and fever relief. Follow the product label and professional advice.',22.00,80,FALSE
WHERE NOT EXISTS (SELECT 1 FROM medicines WHERE name='Paracetamol 500 mg');

INSERT INTO medicines(name,category,description,price,stock_quantity,prescription_required)
SELECT 'Vitamin C Tablets','Wellness','Vitamin C supplement. Use according to label directions and professional advice.',95.00,45,FALSE
WHERE NOT EXISTS (SELECT 1 FROM medicines WHERE name='Vitamin C Tablets');

INSERT INTO medicines(name,category,description,price,stock_quantity,prescription_required)
SELECT 'Antiseptic Cream','First Aid','Topical antiseptic first-aid product for minor skin care. Follow package directions.',68.00,32,FALSE
WHERE NOT EXISTS (SELECT 1 FROM medicines WHERE name='Antiseptic Cream');

INSERT INTO medicines(name,category,description,price,stock_quantity,prescription_required)
SELECT 'Oral Rehydration Salts','Digestive Health','Oral rehydration product. Prepare exactly as directed on the packet.',24.00,60,FALSE
WHERE NOT EXISTS (SELECT 1 FROM medicines WHERE name='Oral Rehydration Salts');

INSERT INTO medicines(name,category,description,price,stock_quantity,prescription_required)
SELECT 'Cetirizine 10 mg','Wellness','Antihistamine product. Check warnings and consult a pharmacist before use.',18.00,40,FALSE
WHERE NOT EXISTS (SELECT 1 FROM medicines WHERE name='Cetirizine 10 mg');

INSERT INTO medicines(name,category,description,price,stock_quantity,prescription_required)
SELECT 'Amoxicillin 500 mg','Wellness','Prescription-only antibiotic listing for workflow demonstration; not available for demo checkout.',75.00,20,TRUE
WHERE NOT EXISTS (SELECT 1 FROM medicines WHERE name='Amoxicillin 500 mg');

INSERT INTO medicines(name,category,description,price,stock_quantity,prescription_required)
SELECT 'Digital Thermometer','First Aid','Reusable digital thermometer for temperature measurement.',145.00,25,FALSE
WHERE NOT EXISTS (SELECT 1 FROM medicines WHERE name='Digital Thermometer');

INSERT INTO medicines(name,category,description,price,stock_quantity,prescription_required)
SELECT 'Calcium Tablets','Wellness','Calcium supplement listing. Ask a healthcare professional whether it is appropriate for you.',110.00,30,FALSE
WHERE NOT EXISTS (SELECT 1 FROM medicines WHERE name='Calcium Tablets');
