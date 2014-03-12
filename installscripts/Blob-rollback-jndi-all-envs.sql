/* delete resource properties */
delete from resource_properties where resource_properties.resource_id in (select resource_id from resources where resources.APPLICATION_ID in (select applications.application_id from applications where application_name = 'BlobServerWebService'));

/* delete resources */
delete from resources where resources.APPLICATION_ID in (select applications.application_id from applications where application_name = 'BlobServerWebService');

/* delete application */
delete from applications where application_name = 'BlobServerWebService';