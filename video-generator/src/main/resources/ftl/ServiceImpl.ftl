package ${package_name}.service.impl;

import org.springframework.stereotype.Service;
import ${package_name}.domain.${table_name};
import ${package_name}.mapper.${table_name}Mapper;
import ${package_name}.service.${table_name}Service;
import top.yinjinbiao.video.common.service.impl.AbstractBaseServiceImpl;

@Service
public class ${table_name}ServiceImpl extends AbstractBaseServiceImpl<${table_name}, ${table_name}Mapper> implements ${table_name}Service {

}
