# Provider configuration
provider "azurerm" {
  features {}
}

# Module declarations
module "hub" {
  source = "./modules/hub"

  # Add input variables for the hub module here
}

module "spoke" {
  source = "./modules/spoke"

  # Add input variables for the spoke module here
}

module "security" {
  source = "./modules/security"

  # Add input variables for the security module here
}

module "firewall" {
  source = "./modules/firewall"

  # Add input variables for the firewall module here
}

module "frontdoor" {
  source = "./modules/frontdoor"

  # Add input variables for the front door module here
}

module "jumpbox" {
  source = "./modules/jumpbox"

  # Add input variables for the jumpbox module here
}

module "storage" {
  source = "./modules/storage"

  # Add input variables for the storage module here
}

module "database" {
  source = "./modules/database"

  # Add input variables for the database module here
}

module "postgresql" {
  source = "./modules/postgresql"

  # Add input variables for the PostgreSQL module here
}

module "aks" {
  source = "./modules/aks"
  aks_cluster_name  = "aks-cluster"
  location          = "eastus"
  resource_group_name = module.spoke.resource_group_name
  aks_dns_prefix    = "aks"
  aks_node_count    = 3
  aks_node_vm_size  = "Standard_DS2_v2"
  aks_subnet_id     = module.spoke.database_subnet_id
}

# Add the main Terraform configuration for the secure landing zone here

# Output values
output "hub_output" {
  value = module.hub.output_value
}

output "spoke_output" {
  value = module.spoke.output_value
}

output "security_output" {
  value = module.security.output_value
}

output "firewall_output" {
  value = module.firewall.output_value
}

output "frontdoor_output" {
  value = module.frontdoor.output_value
}

output "jumpbox_output" {
  value = module.jumpbox.output_value
}

output "storage_output" {
  value = module.storage.output_value
}

output "database_output" {
  value = module.database.output_value
}

output "postgresql_output" {
  value = module.postgresql.output_value
}

output "aks_output" {
  value = module.aks.output_value
}
