ALTER TABLE events
ADD CONSTRAINT fk_events_users
FOREIGN KEY (user_id) REFERENCES users(id);

ALTER TABLE events
ADD CONSTRAINT fk_events_file
FOREIGN KEY (file_id) REFERENCES files(id);

ALTER TABLE files
ADD CONSTRAINT fk_files_users
FOREIGN KEY (user_id) REFERENCES users(id);