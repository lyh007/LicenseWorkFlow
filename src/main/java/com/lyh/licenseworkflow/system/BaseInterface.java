package com.lyh.licenseworkflow.system;

/**
 * 公共接口，包括添加、修改、删除、通过id查询、分页查询、查询总数
 *
 * @author kevin
 * @version Revision: 1.00 Date: 11-9-22下午6:52
 * @Email liuyuhui007@gmail.com
 */
public interface BaseInterface<T> {
    /**
     * 保存T类型对象到数据库中
     *
     * @param entity 对象信息
     */
    public long save(T entity);

    /**
     * 修改记录信息
     *
     * @param entity 要修改的对象信息
     */
    public void update(T entity);

    /**
     * 删除数据库中指定ID的记录
     *
     * @param id 要删除的对象标识
     */
    public void delete(Long id);


    /**
     * 通过id查询数据库记录
     *
     * @param id 唯一标识
     * @return 对象信息
     */
    public T getById(Long id);

    /**
     * 分页查询
     * @param criteria 查询条件
     * @return 对象列表
     */
}
